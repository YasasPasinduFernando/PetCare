package org.agile.petcare.Service;

import org.agile.petcare.Dto.AppointmentRequestDTO;
import org.agile.petcare.Model.Appointment;
import org.agile.petcare.Model.Owner;
import org.agile.petcare.Model.Pet;
import org.agile.petcare.Model.Vet;
import org.agile.petcare.Repository.AppointmentRepository;
import org.agile.petcare.Repository.OwnerRepository;
import org.agile.petcare.Repository.PetRepository;
import org.agile.petcare.Repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private VetRepository vetRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Appointment createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Pet pet = petRepository.findById(appointmentRequestDTO.getPetId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        Owner owner = ownerRepository.findById(appointmentRequestDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentRequestDTO.getAppointmentTime());
        appointment.setReason(appointmentRequestDTO.getReason());
        appointment.setStatus("Pending"); // Default status

        appointment.setPet(pet);
        appointment.setOwner(owner);
        appointment.setVet(null);

        return appointmentRepository.save(appointment);
    }

    public Appointment assignVet(Long appointmentId, Long vetId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Vet not found"));

        appointment.setVet(vet);
        return appointmentRepository.save(appointment);
    }

    public Appointment changeStatus(Long appointmentId, String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + appointmentId));
    }


    public List<Appointment> getAppointmentsByOwner(Long ownerId) {
        return appointmentRepository.findByOwnerId(ownerId);
    }

    public List<Appointment> getAppointmentsByVet(Long vetId) {
        return appointmentRepository.findByVetIdAndStatusNotIgnoreCase(vetId, "Cancel");
    }


    public List<Appointment> getAppointmentsByPet(Long petId) {
        return appointmentRepository.findByPetId(petId);
    }

    public Appointment updateAppointment(Long appointmentId, AppointmentRequestDTO dto) {
        try {
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + appointmentId));

            appointment.setAppointmentDate(dto.getAppointmentDate());
            appointment.setAppointmentTime(dto.getAppointmentTime());
            appointment.setReason(dto.getReason());
            appointment.setStatus(dto.getStatus());

            // Update Pet
            if (dto.getPetId() != null) {
                Pet pet = petRepository.findById(dto.getPetId())
                        .orElseThrow(() -> new IllegalArgumentException("Pet not found with ID: " + dto.getPetId()));
                appointment.setPet(pet);
            }

            // Update Vet (optional)
            if (dto.getVetId() != null) {
                Vet vet = vetRepository.findById(dto.getVetId())
                        .orElse(null); // It's okay if vet is null
                appointment.setVet(vet);
            } else {
                appointment.setVet(null); // clear vet if not provided
            }

            // Update Owner
            if (dto.getOwnerId() != null) {
                Owner owner = ownerRepository.findById(dto.getOwnerId())
                        .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + dto.getOwnerId()));
                appointment.setOwner(owner);
            }

            return appointmentRepository.save(appointment);

        } catch (IllegalArgumentException e) {
            throw e; // Re-throw for controller to catch and return 400
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while updating appointment: " + e.getMessage(), e);
        }
    }



}
