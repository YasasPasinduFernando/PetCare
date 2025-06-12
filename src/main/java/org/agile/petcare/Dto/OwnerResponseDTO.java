package org.agile.petcare.Dto;

import lombok.Data;

@Data
public class OwnerResponseDTO {
    private Long id;
    private String fname;
    private String email;
    private String phone;
    private String username;
}
