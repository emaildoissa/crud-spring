package com.appsigel.crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public record ClienteDTO(
        Long id, 
        @NotBlank @NotNull @Length(min = 3, max = 100) String nome, 
        @NotBlank @NotNull @Length(max = 13) String telefone,
        List<OrdemDTO> ordens){  
}
