package br.com.zup.desafio.marvel.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.desafio.marvel.entities.Usuario;



public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo nome obrigatório")
	private String nome;
	
	@NotBlank(message = "Campo email obrigatório")
	@Email(message = "Por favor entrar com email válido")
	private String email;
	
	@NotBlank(message = "Campo cpf obrigatório")
	@CPF(message = "Por favor entrar com cpf válido")
	private String cpf;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Campo data de nascimento obrigatório")
	private Date dataNascimento;
	
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Long id, String nome, String email, String cpf, Date dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.cpf = entity.getCpf();
		this.dataNascimento = entity.getDataNascimento();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
