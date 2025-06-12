package org.agile.petcare.Model;

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
    private String digree;
    private String bio;
    private String status;

    @OneToMany(mappedBy = "vet")
    private List<Pet> pets;
}