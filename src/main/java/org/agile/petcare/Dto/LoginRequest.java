package org.agile.petcare.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailAddress;
    private String password;
}