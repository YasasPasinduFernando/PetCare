package org.agile.petcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;
}
