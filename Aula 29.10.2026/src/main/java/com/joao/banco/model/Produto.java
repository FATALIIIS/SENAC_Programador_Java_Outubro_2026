package com.joao.banco.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codPro;
	
	@ManyToOne
	@JoinColumn(name="codFab",nullable = false)
	private Fabricante fabricante;
	
	@Column(name = "nome",length = 50)
	private String nome;
	
	@Column(name="descricao",columnDefinition = "TEXT")
	private String descricao;
	
	@Column(name = "quantidade")
	private int quantidade;
	
	@Column(name = "preco",precision=6,scale=2)
	private BigDecimal preco;
	
	@Column(name="foto",columnDefinition = "bytea")
	private byte[] foto;
	
	public Produto() {
		//construtor
	}

	public Integer getCodPro() {
		return codPro;
	}

	public void setCodPro(Integer codPro) {
		this.codPro = codPro;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
}
