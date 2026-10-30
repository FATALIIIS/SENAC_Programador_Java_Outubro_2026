package com.joao.banco.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joao.banco.model.Produto;
import com.joao.banco.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository=produtoRepository;
	}
	
	@Transactional
	public boolean salvar(Produto produto) {
		try {
			produtoRepository.save(produto);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
