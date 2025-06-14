package org.agile.petcare.Controller;

import org.agile.petcare.Dto.LoginRequest;
import org.agile.petcare.Model.Owner;
import org.agile.petcare.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public Optional<Owner> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
    }


    @PostMapping("/login")
    public Owner login(@RequestBody LoginRequest loginRequest) {
        return ownerService.loginOwner(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
