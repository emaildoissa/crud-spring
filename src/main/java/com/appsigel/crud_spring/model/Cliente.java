package com.appsigel.crud_spring.model;

import com.appsigel.crud_spring.enums.Status;
import com.appsigel.crud_spring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE CLIENTES SET status = 'Inativo' Where id = ?")
@Where(clause = "status = 'Ativo'")
@Table(name = "clientes")

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @NotBlank
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull
    @NotBlank
    @Length(max = 14)
    @Column(length = 100, nullable = false)
    private String telefone;
    
    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;
    
    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    @JsonManagedReference
    private List<Ordem> ordens = new ArrayList<>();
}
