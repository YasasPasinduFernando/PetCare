package org.agile.petcare.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String petName;
    private String petSpecies;
    private String petBreed;
    private LocalDate petDob;
    private String petGender;

    private String petMicrochipId;

    private String profilePicture;

    private String medicalHistory;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;


}
