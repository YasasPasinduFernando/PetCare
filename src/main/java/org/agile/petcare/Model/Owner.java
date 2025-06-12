package org.agile.petcare.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    private String phoneNumber;
    private String username;
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;



}
