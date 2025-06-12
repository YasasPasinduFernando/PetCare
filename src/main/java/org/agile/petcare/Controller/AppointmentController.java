package org.agile.petcare.Controller;

import org.agile.petcare.Dto.AppointmentRequestDTO;
import org.agile.petcare.Model.Appointment;
import org.agile.petcare.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ Create an appointment with Try-Catch
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        try {
            Appointment createdAppointment = appointmentService.createAppointment(appointmentRequestDTO);
            return ResponseEntity.ok(createdAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating appointment: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    // ✅ Get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ✅ Get appointment by Appointment ID
    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long appointmentId) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            if (appointment != null) {
                return ResponseEntity.ok(appointment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Appointment not found with ID: " + appointmentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching appointment: " + e.getMessage());
        }
    }


    // ✅ Get appointments by owner ID
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> getAppointmentsByOwner(@PathVariable Long ownerId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByOwner(ownerId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching owner's appointments: " + e.getMessage());
        }
    }

    // ✅ Get appointments by vet ID
    @GetMapping("/vet/{vetId}")
    public ResponseEntity<?> getAppointmentsByVet(@PathVariable Long vetId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByVet(vetId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching vet's appointments: " + e.getMessage());
        }
    }

    // ✅ Get appointments by pet ID
    @GetMapping("/pet/{petId}")
    public ResponseEntity<?> getAppointmentsByPet(@PathVariable Long petId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByPet(petId);
            return ResponseEntity.ok(appointments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching pet's appointments: " + e.getMessage());
        }
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<?> updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody AppointmentRequestDTO updatedAppointmentDTO) {
        try {
            Appointment updatedAppointment = appointmentService.updateAppointment(appointmentId, updatedAppointmentDTO);
            return ResponseEntity.ok(updatedAppointment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Appointment not found or could not be updated: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

}
