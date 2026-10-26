package com.joao.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.banco.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer>{
	
	public Administrador findByUsuario(String Usuario);
}
