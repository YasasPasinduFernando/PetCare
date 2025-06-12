package org.agile.petcare.Service;

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

    public Appointment createAppointment(Appointment appointment, Long petId, Long vetId, Long ownerId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        Vet vet = vetRepository.findById(vetId)
                .orElseThrow(() -> new RuntimeException("Vet not found"));
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        appointment.setPet(pet);
        appointment.setVet(vet);
        appointment.setOwner(owner);
        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByOwner(Long ownerId) {
        return appointmentRepository.findByOwnerId(ownerId);
    }

    public List<Appointment> getAppointmentsByVet(Long vetId) {
        return appointmentRepository.findByVetId(vetId);
    }

    public List<Appointment> getAppointmentsByPet(Long petId) {
        return appointmentRepository.findByPetId(petId);
    }
}
