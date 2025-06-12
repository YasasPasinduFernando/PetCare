package org.agile.petcare.Controller;

import org.agile.petcare.Model.Pet;
import org.agile.petcare.Model.Vet;
import org.agile.petcare.Service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    @Autowired
    private VetService vetService;

    @PostMapping
    public Vet createVet(@RequestBody Vet vet) {
        return vetService.createVet(vet);
    }

    @GetMapping
    public List<Vet> getAllVets() {
        return vetService.getAllVets();
    }

    @GetMapping("/{id}")
    public Vet getVetById(@PathVariable Long id) {
        return vetService.getVetById(id);
    }

    @GetMapping("/{id}/pets")
    public List<Pet> getPetsByVet(@PathVariable Long id) {
        return vetService.getVetById(id).getPets();
    }

}
