package com.joao.banco.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			System.out.println(e);
			
			resposta="Erro na tentativa de cadastrar!";
			model.addAttribute("resposta",resposta);
		}
		model.addAttribute("fabricante", new Fabricante());
		return "cadastrarfabricante";
	}
	
	@GetMapping("/fabricante/listar")
	public String listar(Model model,@PageableDefault(size=5,sort = "codFab")Pageable pageable) {
		Page<Fabricante> pagina=fabricanteService.listar(pageable);	
		model.addAttribute("fabricantes", pagina.getContent());
		model.addAttribute("paginaAtual", pagina.getNumber());
		model.addAttribute("totalPaginas", pagina.getTotalPages());
		model.addAttribute("total", pagina.getTotalElements());
		return "listarfabricante";
	}
	
	@GetMapping("/fabricante/buscar")
	public String buscar(
			@RequestParam(name="campo",required=false) String campo,
			@RequestParam(name="valor",required=false) String valor,
			@PageableDefault(size = 5, sort="codFab") Pageable pageable,
			Model model
			) {
		Page<Fabricante> pagina;
		if(campo !=null && valor!=null && !valor.trim().isEmpty()) {
			pagina=fabricanteService.buscar(pageable, campo, valor);
		}else {
			pagina=fabricanteService.listar(pageable);
		}
		
		model.addAttribute("fabricantes",pagina.getContent());
		model.addAttribute("paginaAtual",pagina.getNumber());
		model.addAttribute("totalPaginas",pagina.getTotalPages());
		model.addAttribute("total",pagina.getTotalElements());
		
		model.addAttribute("campo",campo);
		model.addAttribute("valor",valor);
		
		return "buscarfabricante";
	}
	
	@GetMapping("/fabricante/alterar")
	public String alterar(@RequestParam(name="codFab") Integer codFab,
			Model model) {
		Fabricante fabricante=fabricanteService.buscarPorCodigo(codFab);
		model.addAttribute("fabricante",fabricante);
		return "alterarfabricante";
	}
	
	@PostMapping("/fabricante/alterar")
	public String alterar(
			@RequestParam("codFab") Integer codFab,
			Fabricante fabricante,
			Model model
			) {
		fabricante.setCodFab(codFab);
		
		Fabricante velhoFab=fabricanteService.buscarPorCodigo(codFab);
		if(velhoFab==null) {
			model.addAttribute("resposta","Erro: Fabricante não encontrado!");
			model.addAttribute("fabricante",fabricante);
			return "alterarfabricante";
		}
		String cnpjNovoLimpo=fabricante.getCnpj()!=null?fabricante.getCnpj().replaceAll("[^0-9]",""):"";
		String cnpjVelhoLimpo=fabricante.getCnpj()!=null?velhoFab.getCnpj().replaceAll("[^0-9]",""):"";
		if(!cnpjNovoLimpo.equals(cnpjVelhoLimpo) && fabricanteService.verificarCnpj(fabricante.getCnpj())) {
			model.addAttribute("resposta","CNPJ já cadastrado!");
			model.addAttribute("fabricante", fabricante);
			return "alterarfabricante";
		}
		boolean resultado=fabricanteService.atualizar(codFab, fabricante);
		String resposta=(resultado)?"Alterado com sucesso!": "Erro na tentativa de alterar!";
		model.addAttribute("resposta",resposta);
		model.addAttribute("fabricante", fabricante);
		return "alterarfabricante";
	}
	
	@GetMapping("/fabricante/excluir")
	public String excluir(
			@RequestParam(name = "codFab",required=true) Integer codFab,
			Model model) {
		Fabricante fabricante=fabricanteService.buscarPorCodigo(codFab);
		model.addAttribute("fabricante", fabricante);
		return "excluirfabricante";
	}
	
	@PostMapping("/fabricante/excluir")
	public String confirmaExcluir(@RequestParam("codFab") Integer codFab, Model model) {
		boolean resultado=fabricanteService.delete(codFab);
		if(resultado) {
			model.addAttribute("resposta", true);
			return "fabricanteexcluido";
		}else {
			model.addAttribute("resposta","Erro na tentativa de excluir!");
			return "excluirfabricante";
		}
	}
}