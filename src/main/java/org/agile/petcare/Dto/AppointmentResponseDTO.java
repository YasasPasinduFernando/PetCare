package org.agile.petcare.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponseDTO {
    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status; // e.g., PENDING, CONFIRMED, CANCELLED

    private Long petId;    // Only the ID, not the whole Pet object
    private String petName; // Direct field for Pet Name

    private Long vetId;    // Only the ID
    private Long ownerId;  // Only the ID
}
