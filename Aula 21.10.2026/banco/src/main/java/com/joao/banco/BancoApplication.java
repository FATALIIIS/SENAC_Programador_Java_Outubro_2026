package com.joao.banco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.joao.banco.model.Administrador;
import com.joao.banco.repository.AdministradorRepository;

@SpringBootApplication
public class BancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner iniciarDatabase(AdministradorRepository repository) {
		return args->{
			if(repository.count()==0) {
				Administrador administrador=new Administrador();
				administrador.setNome("Genésio Ribeiro Button");
				administrador.setUsuario("admin");
				administrador.setSenha("admin");
				administrador.criptografa();
				repository.save(administrador);
			}
		};
	}
}
