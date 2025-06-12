package org.agile.petcare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status; // e.g., PENDING, CONFIRMED, CANCELLED

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne(optional = true) // ✅ Vet is optional now
    @JoinColumn(name = "vet_id", nullable = true) // can be null in DB
    private Vet vet;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
