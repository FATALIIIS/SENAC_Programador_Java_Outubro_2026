package com.joao.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.joao.banco.model.Fabricante;
import com.joao.banco.service.FabricanteService;

@Controller
public class FabricanteController {
	
	private final FabricanteService fabricanteService;
	
	public FabricanteController(FabricanteService fabricanteService) {
		this.fabricanteService=fabricanteService;
	}
	
	@GetMapping("/fabricante/cadastrar")
	public String salvar(Model model) {
		model.addAttribute("fabricante",new Fabricante());
		return "cadastrarfabricante";
	}
	
	@PostMapping("/fabricante/cadastrar")
	public String salvar(Fabricante fabricante,Model model) {
		String resposta="";
		try {
			boolean resultado=fabricanteService.salvar(fabricante);
			resposta=(resultado)?"Cadastrado com Sucesso!":
				"CNPJ já cadastrado!";
			model.addAttribute("resposta", resposta);
		}catch (Exception e) {
			resposta="Erro na tentativa de cadastrar!";
			model.addAttribute("resposta",resposta);
		}
		
	}

}