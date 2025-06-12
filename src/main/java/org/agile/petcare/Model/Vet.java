package org.agile.petcare.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String degree; // Corrected spelling
    private String bio;
    private String status;

    @OneToMany(mappedBy = "vet")
    @JsonIgnore
    private List<Pet> pets;

    @ManyToOne(optional = true) // This is correct
    @JoinColumn(name = "vet_id", nullable = true)
    private Vet vet;
}
