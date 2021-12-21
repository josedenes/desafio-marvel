package br.com.zup.desafio.marvel.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafio.marvel.dto.UsuarioDTO;
import br.com.zup.desafio.marvel.entities.Usuario;
import br.com.zup.desafio.marvel.repositories.UsuarioRepository;
import br.com.zup.desafio.marvel.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
		List<Usuario> list = repository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id); 
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada"));
		return new UsuarioDTO(entity);
	}
	
	
}
