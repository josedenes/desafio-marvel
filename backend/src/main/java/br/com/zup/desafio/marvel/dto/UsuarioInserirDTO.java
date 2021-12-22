package br.com.zup.desafio.marvel.dto;

import br.com.zup.desafio.marvel.services.validation.UserInsertValid;

@UserInsertValid
public class UsuarioInserirDTO extends UsuarioDTO{
	private static final long serialVersionUID = 1L;
	
	public UsuarioInserirDTO() {
		super();
	}

}
