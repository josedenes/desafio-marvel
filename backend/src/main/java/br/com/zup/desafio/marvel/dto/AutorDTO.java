package br.com.zup.desafio.marvel.dto;


import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import br.com.zup.desafio.marvel.entities.Autor;


public class AutorDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo nome obrigatório")
	private String nome;
	
	
	public AutorDTO() {
		
	}
	
	public AutorDTO(Long id, String nome, String email, String cpf, Date dataNascimento) {
		this.id = id;
		this.nome = nome;
	}
	
	public AutorDTO(Autor entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
