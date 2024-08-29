package com.appsigel.crud_spring.service;

import com.appsigel.crud_spring.dto.ClienteDTO;
import com.appsigel.crud_spring.dto.ClientePageDTO;
import com.appsigel.crud_spring.dto.mapper.ClienteMapper;
import com.appsigel.crud_spring.model.Cliente;
import com.appsigel.crud_spring.repository.ClienteRepository;
import exception.RecordNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated

public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
     
    public ClientePageDTO list( @PositiveOrZero int page, @Positive @Max(1000) int pageSize) {
      Page<Cliente> pageCliente = clienteRepository.findAll(PageRequest.of(page, pageSize));
      List <ClienteDTO> clientes = pageCliente.get().map(clienteMapper::toDTO).collect(Collectors.toList());
      return new ClientePageDTO(clientes, pageCliente.getTotalElements(), pageCliente.getTotalPages());
      
    }

    public ClienteDTO findById(@NotNull @Positive Long id){
        return clienteRepository.findById(id).map(clienteMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));          
    }
     
    public ClienteDTO create(@Valid @NotNull ClienteDTO clienteDTO){
        return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(clienteDTO)));
    }
    
    
    public ClienteDTO update(@NotNull @Positive Long id, @Valid @NotNull ClienteDTO clienteDTO ){
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    Cliente cliente = clienteMapper.toEntity(clienteDTO);
                    recordFound.setNome(clienteDTO.nome());
                    recordFound.setTelefone(clienteDTO.telefone());
                    recordFound.getOrdens().clear();
                    cliente.getOrdens().forEach(recordFound.getOrdens()::add); 
                    return clienteMapper.toDTO(clienteRepository.save(recordFound));
                }).orElseThrow(()-> new RecordNotFoundException(id));
    }
    
     public void delete(@NotNull @Positive Long id){
        clienteRepository.delete(clienteRepository.findById(id)
                .orElseThrow(()-> new RecordNotFoundException(id)));
    }
}