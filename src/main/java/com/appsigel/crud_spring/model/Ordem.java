package com.appsigel.crud_spring.model;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ordem")

public class Ordem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  
    
    @NotNull
    @NotBlank
    @Length(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String marca;
    //private String tipo;
    //private String acessorios;
    //private String defeito;
    //private String obs;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
   // @JsonProperty( access = JsonProperty.WRITE_ONLY)
    @JsonBackReference
    private Cliente cliente;
}
