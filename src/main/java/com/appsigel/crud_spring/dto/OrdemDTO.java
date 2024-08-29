package com.appsigel.crud_spring.dto;

import org.hibernate.validator.constraints.Length;
import com.appsigel.crud_spring.enums.Situacao;
import com.appsigel.crud_spring.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record OrdemDTO(
    Long id,
    @NotBlank  @Length(min = 1, max = 100)
    String marca,
    @Valid @ValueOfEnum(enumClass = Situacao.class) Situacao situacao
   ) {
           
}
