package org.agile.petcare.Service;

import org.agile.petcare.Model.Vet;
import org.agile.petcare.Repository.VetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {

    @Autowired
    private VetRepository vetRepository;

    public Vet createVet(Vet vet) {
        return vetRepository.save(vet);
    }

    public List<Vet> getAllVets() {
        return vetRepository.findAll();
    }

    public Vet getVetById(Long id) {
        return vetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vet not found"));
    }
}
