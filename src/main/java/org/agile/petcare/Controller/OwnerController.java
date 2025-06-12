package org.agile.petcare.Controller;

import org.agile.petcare.Dto.LoginRequest;
import org.agile.petcare.Model.Owner;
import org.agile.petcare.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
@CrossOrigin
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<?> createOwner(@RequestBody Owner owner) {
        try {
            Owner createdOwner = ownerService.createOwner(owner);
            return ResponseEntity.ok(createdOwner);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating owner: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOwners() {
        try {
            List<Owner> owners = ownerService.getAllOwners();
            return ResponseEntity.ok(owners);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving owners: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long id) {
        try {
            Optional<Owner> owner = ownerService.getOwnerById(id);
            return owner.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error retrieving owner: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long id) {
        try {
            ownerService.deleteOwner(id);
            return ResponseEntity.ok("Owner deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting owner: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Owner owner = ownerService.loginOwner(loginRequest.getEmailAddress(), loginRequest.getPassword());
            if (owner != null) {
                return ResponseEntity.ok(owner);
            } else {
                return ResponseEntity.status(401).body("Invalid email or password.");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Login failed: " + e.getMessage());
        }
    }
}
