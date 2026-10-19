package com.joao.banco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.joao.banco.model.Administrador;
import com.joao.banco.service.AdministradorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	private final AdministradorService administradorService;
	
	public HomeController(AdministradorService administradorService) {
		this.administradorService=administradorService;
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String logar(Model model) {
		model.addAttribute("administrador",new Administrador());
		return "login";
	}
	
	@PostMapping("/login")
	public String fazerLogin(Model model, HttpSession session, @ModelAttribute Administrador administrador) {
		Administrador admAutenticado=administradorService.autenticar(administrador.getUsuario(),administrador.getSenha());
		
		if(admAutenticado!=null) {
			session.setAttribute("admin", admAutenticado);
			model.addAttribute("resposta",true);
		}else {
			model.addAttribute("resposta",false);
		}
		return "login";
	}
}
