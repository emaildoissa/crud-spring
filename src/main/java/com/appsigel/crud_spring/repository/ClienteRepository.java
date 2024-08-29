package com.appsigel.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appsigel.crud_spring.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
  
}
