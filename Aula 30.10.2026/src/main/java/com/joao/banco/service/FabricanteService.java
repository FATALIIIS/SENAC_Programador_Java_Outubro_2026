package com.joao.banco.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public Fabricante buscarPorCodigo(Integer codFab) {
		Fabricante fabricante=repository.findByCodFab(codFab);
		return fabricante;
	}
	
	public boolean verificarCnpj(String novoCnpj) {
		Fabricante fabricante=repository.findByCnpj(novoCnpj);
		if(fabricante==null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Transactional
	public boolean atualizar(Integer id,Fabricante dadosNovos) {
		Fabricante fabricante = repository.findByCodFab(id);
		if(fabricante == null) {
			return false;
		}
		String novoCnpj=dadosNovos.getCnpj();
		if(novoCnpj!=null) {
			novoCnpj=novoCnpj.replaceAll("^[0-9]","");
			fabricante.setCnpj(novoCnpj);
		}
		fabricante.setNome(dadosNovos.getNome());
		fabricante.setEndereco(dadosNovos.getEndereco());
		fabricante.setTelefone(dadosNovos.getTelefone());
		
		repository.save(fabricante);
		return true;
	}
	
	@Transactional
	public boolean delete(Integer codFab) {
		Fabricante fabricante=repository.findByCodFab(codFab);
		if(fabricante==null) {
			return false;
		}
		repository.delete(fabricante);
		return true;
	}
	
	public List<Fabricante> listarPorNome(){
		return repository.findAll(Sort.by(Sort.Direction.ASC,"nome"));
	}
	
}
