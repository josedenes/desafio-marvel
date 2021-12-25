package br.com.zup.desafio.marvel.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.zup.desafio.marvel.entities.Comic;


public class ComicDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo comicId obrigatório")
	private Long comicId;
	
	@NotBlank(message = "Campo titulo obrigatório")
	private String titulo;
	
	@NotBlank(message = "Campo preço obrigatório")
	private Double preco;
	
	private String autores;
	
	@NotBlank(message = "Campo isbn obrigatório")
	private String isbn;
	
	private String descricao;
	
	private Boolean aplicaDesconto;
	
	public ComicDTO() {
		
	}

	public ComicDTO(Long comicId, String titulo, Double preco, String autores, String isbn, String descricao,
			Boolean aplicaDesconto) {
		this.comicId = comicId;
		this.titulo = titulo;
		this.preco = preco;
		this.autores = autores;
		this.isbn = isbn;
		this.descricao = descricao;
		this.aplicaDesconto = aplicaDesconto;
	}
	
	public ComicDTO(Comic entity) {
		this.comicId = entity.getComicId();
		this.titulo = entity.getTitulo();
		this.preco = entity.getPreco();
		this.autores = entity.getAutores();
		this.isbn = entity.getIsbn();
		this.descricao = entity.getDescricao();
		this.aplicaDesconto = entity.getAplicaDesconto();
	}

	public Long getComicId() {
		return comicId;
	}

	public void setComicId(Long comicId) {
		this.comicId = comicId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAplicaDesconto() {
		return aplicaDesconto;
	}

	public void setAplicaDesconto(Boolean aplicaDesconto) {
		this.aplicaDesconto = aplicaDesconto;
	}

}
