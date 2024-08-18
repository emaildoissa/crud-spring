package com.appsigel.crud_spring.model;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import com.appsigel.crud_spring.enums.Situacao;
import com.appsigel.crud_spring.enums.converters.SituacaoConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Convert;
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
@SQLDelete(sql = "UPDATE ORDEM SET SITUACAO = 'Inativa' Where id = ?")
//@Where(clause = "status = 'Aberta'")
@SQLRestriction("situacao <> 'ABERTA'")
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

    @NotNull
    
    @Column(length = 10, nullable = false)
    @Convert(converter = SituacaoConverter.class)
    private Situacao situacao = Situacao.ABERTA;

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
