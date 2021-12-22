package br.com.zup.desafio.marvel.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String NomeCampo;
	private String messagem;
	
	public FieldMessage() {
		
	}

	public FieldMessage(String nomeCampo, String messagem) {
		super();
		NomeCampo = nomeCampo;
		this.messagem = messagem;
	}

	public String getNomeCampo() {
		return NomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		NomeCampo = nomeCampo;
	}

	public String getMessagem() {
		return messagem;
	}

	public void setMessagem(String messagem) {
		this.messagem = messagem;
	}
	
	
}
