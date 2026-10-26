package com.joao.banco.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.banco.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante,Integer> {
	
	public Fabricante findByCnpj(String cnpj);
	public Fabricante findByCodFab(Integer codFab);
	public Page<Fabricante> findByNomeContainingIgnoreCase(String nome,Pageable pageable);
	public Page<Fabricante> findByCodFab(Integer codFab,Pageable pageable);
	public Page<Fabricante> findByEnderecoContainingIgnoreCase(String endereco,Pageable pageable);
	public Page<Fabricante> findByTelefoneContainingIgnoreCase(String telefone,Pageable pageable);
	public Page<Fabricante> findByCnpjContainingIgnoreCase(String cnpj,Pageable pageable);
}