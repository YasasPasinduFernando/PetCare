package org.agile.petcare.Dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDTO {
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private String status;

    private Long petId;
    private Long vetId;
    private Long ownerId;
}
