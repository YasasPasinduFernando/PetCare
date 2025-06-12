package org.agile.petcare.Controller;

import org.agile.petcare.Model.Pet;
import org.agile.petcare.Model.Vet;
import org.agile.petcare.Service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vets")
@CrossOrigin
public class VetController {

    @Autowired
    private VetService vetService;

    @PostMapping
    public ResponseEntity<?> createVet(@RequestBody Vet vet) {
        try {
            Vet createdVet = vetService.createVet(vet);
            return ResponseEntity.ok(createdVet);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating vet: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllVets() {
        try {
            List<Vet> vets = vetService.getAllVets();
            return ResponseEntity.ok(vets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving vets: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVetById(@PathVariable Long id) {
        try {
            Vet vet = vetService.getVetById(id);
            if (vet != null) {
                return ResponseEntity.ok(vet);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving vet: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<?> getPetsByVet(@PathVariable Long id) {
        try {
            Vet vet = vetService.getVetById(id);
            if (vet != null) {
                return ResponseEntity.ok(vet.getPets());
            } else {
                return ResponseEntity.status(404).body("Vet not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving pets: " + e.getMessage());
        }
    }
}
