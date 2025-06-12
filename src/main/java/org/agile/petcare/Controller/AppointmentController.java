package org.agile.petcare.Controller;

import org.agile.petcare.Model.Appointment;
import org.agile.petcare.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ✅ Create an appointment
    @PostMapping("/pet/{petId}/vet/{vetId}/owner/{ownerId}")
    public Appointment createAppointment(
            @RequestBody Appointment appointment,
            @PathVariable Long petId,
            @PathVariable Long vetId,
            @PathVariable Long ownerId) {

        return appointmentService.createAppointment(appointment, petId, vetId, ownerId);
    }

    // ✅ Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // ✅ Get appointments by owner ID
    @GetMapping("/owner/{ownerId}")
    public List<Appointment> getAppointmentsByOwner(@PathVariable Long ownerId) {
        return AppointmentService.getAppointmentsByOwner(ownerId);
    }

    // ✅ Get appointments by vet ID
    @GetMapping("/vet/{vetId}")
    public List<Appointment> getAppointmentsByVet(@PathVariable Long vetId) {
        return AppointmentService.getAppointmentsByVet(vetId);
    }

    // ✅ Get appointments by pet ID
    @GetMapping("/pet/{petId}")
    public List<Appointment> getAppointmentsByPet(@PathVariable Long petId) {
        return AppointmentService.getAppointmentsByPet(petId);
    }
}
