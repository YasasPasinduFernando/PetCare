package org.agile.petcare.Service;

import org.agile.petcare.Dto.OwnerResponseDTO;
import org.agile.petcare.Dto.PetResponseDTO;
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
            if (petDetails.getPetName() != null) {
                pet.setPetName(petDetails.getPetName());
            }
            if (petDetails.getPetSpecies() != null) {
                pet.setPetSpecies(petDetails.getPetSpecies());
            }
            if (petDetails.getPetBreed() != null) {
                pet.setPetBreed(petDetails.getPetBreed());
            }
            if (petDetails.getPetDob() != null) {
                pet.setPetDob(petDetails.getPetDob());
            }
            if (petDetails.getPetGender() != null) {
                pet.setPetGender(petDetails.getPetGender());
            }
            if (petDetails.getPetMicrochipId() != null) {
                pet.setPetMicrochipId(petDetails.getPetMicrochipId());
            }
            if (petDetails.getProfilePicture() != null) {
                pet.setProfilePicture(petDetails.getProfilePicture());
            }
            if (petDetails.getMedicalHistory() != null) {
                pet.setMedicalHistory(petDetails.getMedicalHistory());
            }
            if (petDetails.getOwner() != null && petDetails.getOwner().getId() != null) {
                pet.setOwner(petDetails.getOwner());
            }
            if (petDetails.getVet() != null && petDetails.getVet().getId() != null) {
                pet.setVet(petDetails.getVet());
            }
            return petRepository.save(pet);
        }).orElseThrow(() -> new RuntimeException("Pet not found with id: " + id));
    }


    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }


    public PetResponseDTO mapToPetResponseDTO(Pet pet) {
        PetResponseDTO dto = new PetResponseDTO();
        dto.setId(pet.getId());
        dto.setPetName(pet.getPetName());
        dto.setPetSpecies(pet.getPetSpecies());
        dto.setPetBreed(pet.getPetBreed());
        dto.setPetDob(pet.getPetDob());
        dto.setPetGender(pet.getPetGender());
        dto.setPetMicrochipId(pet.getPetMicrochipId());
        dto.setProfilePicture(pet.getProfilePicture());
        dto.setMedicalHistory(pet.getMedicalHistory());

        OwnerResponseDTO ownerDTO = new OwnerResponseDTO();
        ownerDTO.setId(pet.getOwner().getId());
        ownerDTO.setFname(pet.getOwner().getFullName());
        ownerDTO.setEmail(pet.getOwner().getEmailAddress());
        ownerDTO.setPhone(pet.getOwner().getPhoneNumber());
        ownerDTO.setUsername(pet.getOwner().getUsername());

        dto.setOwner(ownerDTO);
        return dto;
    }
}
