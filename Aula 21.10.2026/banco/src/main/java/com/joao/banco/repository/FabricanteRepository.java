package com.joao.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.banco.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante,Integer> {
	
	public Fabricante findByCnpj(String cnpj);
	
}