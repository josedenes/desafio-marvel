package br.com.zup.desafio.marvel.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.desafio.marvel.dto.UsuarioInserirDTO;
import br.com.zup.desafio.marvel.entities.Usuario;
import br.com.zup.desafio.marvel.repositories.UsuarioRepository;
import br.com.zup.desafio.marvel.resources.exceptions.FieldMessage;


public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioInserirDTO> {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInserirDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Usuario usuarioEmail = repository.findByEmail(dto.getEmail());
		if (usuarioEmail != null) {
			list.add(new FieldMessage("email", "Email já existe no banco"));
		}
		
		Usuario usuarioCpf = repository.findByCpf(dto.getCpf());
		if (usuarioCpf != null) {
			list.add(new FieldMessage("cpf", "Cpf já existe no banco"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
