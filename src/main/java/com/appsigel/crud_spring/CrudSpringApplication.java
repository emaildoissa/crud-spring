package com.appsigel.crud_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.appsigel.crud_spring.enums.Situacao;
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
                        
                        Ordem ordem1 = new Ordem();
                        ordem1.setMarca("Sony Vaio");
                        ordem1.setCliente(c);
                        ordem1.setSituacao(Situacao.ABERTA);
                        c.getOrdens().add(ordem1);

						Ordem ordem2 = new Ordem();
						ordem2.setSituacao(Situacao.ABERTA);
                        ordem2.setMarca("CCE");
                        ordem2.setCliente(c);
                        
                        c.getOrdens().add(ordem2);
                        
			clienteRepository.save(c);
			
			
		};
	}
}
