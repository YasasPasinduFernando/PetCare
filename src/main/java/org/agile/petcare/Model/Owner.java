package org.agile.petcare.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fname;
    private Integer age;
    private String email;
    private Integer phone;
    private String username;
    private String password;


}
