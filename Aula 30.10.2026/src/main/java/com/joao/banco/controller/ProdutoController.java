package com.joao.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.joao.banco.model.Produto;
import com.joao.banco.service.FabricanteService;
import com.joao.banco.service.ProdutoService;

@Controller
public class ProdutoController {

	private final ProdutoService produtoService;
	private final FabricanteService fabricanteService;
	
	public ProdutoController(ProdutoService produtoService, FabricanteService fabricanteService) {
		this.produtoService=produtoService;
		this.fabricanteService=fabricanteService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("foto");
	}
	
	@GetMapping("/produto/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("fabricantes", fabricanteService.listarPorNome());
		return "cadastrarproduto";
	}
	
	@PostMapping("/produto/cadastrar")
	public String cadastrar(Model model, Produto produto, @RequestParam(value = "foto",required=false)MultipartFile arquivo) {
		try {
			if(arquivo != null && !arquivo.isEmpty ()) {
				produto.setFoto(arquivo.getBytes());
			}
			boolean resultado=produtoService.salvar(produto);
			String resposta=(resultado)?"Cadastrado com sucesso!":"Erro na tentativa de cadastrar!";
			model.addAttribute("resposta",resposta);
		}catch (Exception e) {
			model.addAttribute("resposta","Erro ao processar a foto!");
		}
		model.addAttribute("produto", new Produto());
		model.addAttribute("fabricantes",fabricanteService.listarPorNome());
		return "cadastrarproduto";
	}
}
