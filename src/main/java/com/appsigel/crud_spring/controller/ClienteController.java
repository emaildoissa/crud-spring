package com.appsigel.crud_spring.controller;

import com.appsigel.crud_spring.dto.ClienteDTO;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.appsigel.crud_spring.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
@RestController
@RequestMapping("/api/clientes")


public class ClienteController {
  
    private final ClienteService clienteService;
    
  
    public ClienteController (ClienteService clienteService) {
       
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> list() {
        return clienteService.list();

    }
    
    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable @NotNull @Positive Long id){
        return clienteService.findById(id);
               
    }
    
    @PostMapping
    @ResponseStatus( code = HttpStatus.CREATED)
    public ClienteDTO create(@Valid @RequestBody ClienteDTO clienteDTO){
        return clienteService.create(clienteDTO);
    }
    
    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull ClienteDTO clienteDTO ){
        return clienteService.update(id, clienteDTO);
                
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        clienteService.delete(id);
    }      
}
