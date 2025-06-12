package org.agile.petcare.Controller;

import org.agile.petcare.Model.Owner;
import org.agile.petcare.Model.Pet;
import org.agile.petcare.Service.OwnerService;
import org.agile.petcare.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.agile.petcare.Dto.OwnerResponseDTO;
import org.agile.petcare.Dto.PetResponseDTO;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    // Create new Pet
    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody Pet pet) {
        try {
            // Validate Owner existence
            if (pet.getOwner() == null || pet.getOwner().getId() == null) {
                return ResponseEntity.badRequest().body("Owner ID is missing.");
            }

            Long ownerId = pet.getOwner().getId();
            Optional<Owner> ownerOpt = ownerService.getOwnerById(ownerId);

            if (ownerOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Owner not found with ID: " + ownerId);
            }

            pet.setOwner(ownerOpt.get());

            Pet savedPet = petService.savePet(pet);
            PetResponseDTO responseDTO = petService.mapToPetResponseDTO(savedPet);
            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating Pet: " + e.getMessage());
        }
    }

    // Get all Pets
    @GetMapping
    public ResponseEntity<?> getAllPets() {
        try {
            List<Pet> pets = petService.getAllPets();
            List<PetResponseDTO> responseDTOs = pets.stream()
                    .map(petService::mapToPetResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching Pets: " + e.getMessage());
        }
    }

    // Get Pet by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Long id) {
        try {
            Optional<Pet> petOpt = petService.getPetById(id);
            if (petOpt.isPresent()) {
                PetResponseDTO responseDTO = petService.mapToPetResponseDTO(petOpt.get());
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching Pet: " + e.getMessage());
        }
    }

    // Update Pet
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        try {
            Pet updatedPet = petService.updatePet(id, petDetails);
            PetResponseDTO responseDTO = petService.mapToPetResponseDTO(updatedPet);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating Pet: " + e.getMessage());
        }
    }

    // Delete Pet
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting Pet: " + e.getMessage());
        }
    }
}
