package com.joao.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.banco.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	public Produto findByCodPro(Integer codPro);
	
}
