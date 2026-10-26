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
	
	public Page<Fabricante> buscar(Pageable pageable,String campo,String valor){
		if(valor ==null || valor.trim().isEmpty()) {
			return repository.findAll(pageable);
		}
		switch(campo) {
		case "codigo":
			try {
				return repository.findByCodFab(Integer.parseInt(valor), pageable);
			}catch (Exception e) {
				return Page.empty(pageable);
			}
		case "nome":
			return repository.findByNomeContainingIgnoreCase(valor, pageable);
		case "endereco":
			return repository.findByEnderecoContainingIgnoreCase(valor, pageable);
		case "telefone":
			return repository.findByTelefoneContainingIgnoreCase(valor, pageable);
		case "cnpj":
			return repository.findByCnpjContainingIgnoreCase(valor, pageable);
		default:
			return repository.findAll(pageable);
		}
	}
}
