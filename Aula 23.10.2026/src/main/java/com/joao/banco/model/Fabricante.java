package com.joao.banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fabricante")
public class Fabricante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codFab;
	
	@Column(name = "nome",nullable = false,length = 40)
	private String nome;
	
	@Column(name = "cnpj",nullable = false,unique = true,length = 14)
	private String cnpj;
	
	@Column(name= "endereco",length = 70)
	private String endereco;
	
	@Column(name = "telefone",nullable = false,length = 14)
	private String telefone;
	
	public Fabricante() {
		// declaro
	}

	public Integer getCodFab() {
		return codFab;
	}

	public void setCodFab(Integer codFab) {
		this.codFab = codFab;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
		
}