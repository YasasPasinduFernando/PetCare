package org.agile.petcare.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PetResponseDTO {
    private Long id;
    private String petName;
    private String petSpecies;
    private String petBreed;
    private LocalDate petDob;
    private String petGender;
    private String petMicrochipId;
    private String profilePicture;
    private String medicalHistory;
    private OwnerResponseDTO owner;
}
