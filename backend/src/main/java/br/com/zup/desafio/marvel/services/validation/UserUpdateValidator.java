package br.com.zup.desafio.marvel.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.zup.desafio.marvel.dto.UsuarioAtualizarDTO;
import br.com.zup.desafio.marvel.entities.Usuario;
import br.com.zup.desafio.marvel.repositories.UsuarioRepository;
import br.com.zup.desafio.marvel.resources.exceptions.FieldMessage;

//public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioDTO> {
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UsuarioAtualizarDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UsuarioAtualizarDTO dto, ConstraintValidatorContext context) {
		
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Usuario usuarioEmail = repository.findByEmail(dto.getEmail());
		if (usuarioEmail != null && userId != usuarioEmail.getId()) {
			list.add(new FieldMessage("email", "Email já existe no banco"));
		}
		
		Usuario usuarioCpf = repository.findByCpf(dto.getCpf());
		if (usuarioCpf != null && userId != usuarioCpf.getId()) {
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
