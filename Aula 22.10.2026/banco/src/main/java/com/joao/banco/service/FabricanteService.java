package com.joao.banco.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joao.banco.model.Fabricante;
import com.joao.banco.repository.FabricanteRepository;

import jakarta.transaction.Transactional;

@Service
public class FabricanteService {

	private final FabricanteRepository repository;
	
	public FabricanteService(FabricanteRepository repository) {
		this.repository=repository;
	}
	
	@Transactional
	public boolean salvar(Fabricante fabricante) {
		if(fabricante.getCnpj()!=null) {
			fabricante.setCnpj(fabricante.getCnpj().replaceAll("[^0-9]",""));
		}
		if(repository.findByCnpj(fabricante.getCnpj())!=null) {
			return false;
		}
		repository.save(fabricante);
		return true;
	}
	
	public Page<Fabricante> listar(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	
}
