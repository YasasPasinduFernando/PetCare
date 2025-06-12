package org.agile.petcare.Repository;

import org.agile.petcare.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // Optional: find all appointments by owner ID
    List<Appointment> findByOwnerId(Long ownerId);

    // Optional: find all appointments by vet ID
    List<Appointment> findByVetId(Long vetId);

    // Optional: find all appointments for a specific pet
    List<Appointment> findByPetId(Long petId);
}
