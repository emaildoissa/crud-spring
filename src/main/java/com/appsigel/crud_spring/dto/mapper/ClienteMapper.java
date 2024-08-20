package com.appsigel.crud_spring.dto.mapper;

import com.appsigel.crud_spring.dto.ClienteDTO;
import com.appsigel.crud_spring.dto.OrdemDTO;
import com.appsigel.crud_spring.enums.Situacao;
import com.appsigel.crud_spring.model.Cliente;
import com.appsigel.crud_spring.model.Ordem;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO (Cliente cliente){
        if( cliente == null ){
            return null;
        }
        List<OrdemDTO> ordens = cliente.getOrdens()
        .stream()
        .map(ordem -> new OrdemDTO(ordem.getId(), ordem.getMarca(),ordem.getSituacao()))
        .collect(Collectors.toList());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone(),
            ordens);
    }
    
    public Cliente toEntity(ClienteDTO clienteDTO){
                
        if( clienteDTO == null ){
            return null;
        }
        Cliente cliente = new Cliente();
        if( clienteDTO.id()!= null ){
            cliente.setId(clienteDTO.id());
        }
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());

        List<Ordem> ordens = clienteDTO.ordens().stream().map(ordemDTO -> {
            var ordem = new Ordem();
            ordem.setId(ordemDTO.id());
            ordem.setMarca(ordemDTO.marca());
            ordem.setSituacao(ordemDTO.situacao());
            ordem.setCliente(cliente);
            return ordem;
        }).collect(Collectors.toList());
        cliente.setOrdens(ordens);
        return cliente;
    }    
}