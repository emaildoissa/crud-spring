package com.appsigel.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.appsigel.crud_spring.model.Ordem;

public interface OrdemRepository  extends JpaRepository<Ordem, Long>{
   
}
