package com.appsigel.crud_spring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrdemDTO(
    Long id,
    @NotNull @NotBlank  @Length(min = 1, max = 100) String marca) {
    
}
