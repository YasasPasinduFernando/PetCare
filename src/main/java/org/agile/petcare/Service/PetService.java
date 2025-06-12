package org.agile.petcare.Service;

import org.agile.petcare.Model.Pet;
import org.agile.petcare.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet updatePet(Long id, Pet petDetails) {
        return petRepository.findById(id).map(pet -> {
            pet.setPetName(petDetails.getPetName());
            pet.setPetSpecies(petDetails.getPetSpecies());
            pet.setPetBreed(petDetails.getPetBreed());
            pet.setPetDob(petDetails.getPetDob());
            pet.setPetGender(petDetails.getPetGender());
            pet.setPetMicrochipId(petDetails.getPetMicrochipId());
            pet.setProfilePicture(petDetails.getProfilePicture());
            pet.setMedicalHistory(petDetails.getMedicalHistory());
            return petRepository.save(pet);
        }).orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
