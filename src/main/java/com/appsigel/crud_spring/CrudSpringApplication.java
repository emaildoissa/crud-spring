package com.appsigel.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appsigel.crud_spring.model.Cliente;
import com.appsigel.crud_spring.model.Ordem;
import com.appsigel.crud_spring.repository.ClienteRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(ClienteRepository clienteRepository){
		return args -> {
			clienteRepository.deleteAll();

			Cliente c = new Cliente();
			c.setNome("Marcos Felipe Issa");
			c.setTelefone("(51)993257923");
                        
                        Ordem ordem = new Ordem();
                        ordem.setMarca("Sony Vaio");
                        ordem.setCliente(c);
                        
                        c.getOrdens().add(ordem);
                        
			clienteRepository.save(c);
			
		};
	}
}
