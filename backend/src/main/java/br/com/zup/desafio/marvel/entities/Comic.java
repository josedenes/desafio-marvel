
package br.com.zup.desafio.marvel.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_comic")
public class Comic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long comicId;
	private String titulo;
	private Double preco;
	
	@Column(columnDefinition = "TEXT")
	private String autores;
	
	@Column(unique = true)
	private String isbn;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	private Boolean aplicaDesconto;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	public Comic() {

	}

	public Comic(Long comicId, String titulo, Double preco, String autores, String isbn, String descricao,
			Boolean aplicaDesconto, Usuario usuario) {
		this.comicId = comicId;
		this.titulo = titulo;
		this.preco = preco;
		this.autores = autores;
		this.isbn = isbn;
		this.descricao = descricao;
		this.aplicaDesconto = aplicaDesconto;
		this.usuario = usuario;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comicId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comic other = (Comic) obj;
		return Objects.equals(comicId, other.comicId);
	}
}
