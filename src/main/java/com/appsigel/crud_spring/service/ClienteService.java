package com.appsigel.crud_spring.service;

import com.appsigel.crud_spring.dto.ClienteDTO;
import com.appsigel.crud_spring.dto.mapper.ClienteMapper;
import com.appsigel.crud_spring.model.Cliente;
import com.appsigel.crud_spring.repository.ClienteRepository;
import exception.RecordNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Validated

public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }
    
    public List<ClienteDTO> list() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
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