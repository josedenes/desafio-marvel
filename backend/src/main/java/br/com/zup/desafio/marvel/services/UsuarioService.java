package br.com.zup.desafio.marvel.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafio.marvel.dto.ComicDTO;
import br.com.zup.desafio.marvel.dto.UsuarioAtualizarDTO;
import br.com.zup.desafio.marvel.dto.UsuarioDTO;
import br.com.zup.desafio.marvel.dto.UsuarioInserirDTO;
import br.com.zup.desafio.marvel.entities.Comic;
import br.com.zup.desafio.marvel.entities.Usuario;
import br.com.zup.desafio.marvel.repositories.ComicRepository;
import br.com.zup.desafio.marvel.repositories.UsuarioRepository;
import br.com.zup.desafio.marvel.services.exceptions.DatabaseException;
import br.com.zup.desafio.marvel.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Transactional
	public UsuarioDTO insert(UsuarioInserirDTO dto) {
		Usuario entity = new Usuario();
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
		entity.setDataNascimento(dto.getDataNascimento());
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
		List<Usuario> list = repository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> obj = repository.findById(id); 
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrada"));
		return new UsuarioDTO(entity);
	}
	
	//estava assim antes de acrescentar o calculo de desconto
//	@Transactional(readOnly = true)
//	public List<ComicDTO> findComicsPorIdUsuario(Long id){
//		List<Comic> list = comicRepository.findByUsuarioId(id);
//		return list.stream().map(x -> new ComicDTO(x)).collect(Collectors.toList());
//	}
	
	
	@Transactional(readOnly = true)
	public List<ComicDTO> findComicsPorIdUsuario(Long id){
		Optional<Usuario> obj = repository.findById(id); 
		Usuario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrada"));
		
		List<Comic> list = comicRepository.findByUsuarioId(id);
		List<ComicDTO> listComicDTO = new ArrayList<>();
		
		for (Comic comic: list) {
			comic.setAplicaDesconto(descontoAtivado(comic.getIsbn()));;	
			if(comic.getAplicaDesconto()) {
				comic.setPreco(comic.getPreco()*0.9);
			}
			listComicDTO.add(new ComicDTO(comic.getComicId(), comic.getTitulo(), comic.getPreco(), comic.getAutores(), 
					comic.getIsbn(), comic.getDescricao(), comic.getAplicaDesconto()));	
		}
		return listComicDTO;
	}
	
	
	public Boolean descontoAtivado(String isbn){
		DayOfWeek data = LocalDate.now().getDayOfWeek();
		int len = isbn.length();
		char lastChar = isbn.charAt(len - 1);
		String lastCharString = ""+lastChar;
		
		int digitoFinalIsbn = Integer.parseInt(lastCharString);
		int diaDaSemanaIsbn;
		
		if (digitoFinalIsbn == 0 || digitoFinalIsbn == 1) {
			diaDaSemanaIsbn = 1;
        } else if (digitoFinalIsbn == 2 || digitoFinalIsbn == 3) {
        	diaDaSemanaIsbn = 2;
        } else if (digitoFinalIsbn == 4 || digitoFinalIsbn == 5) {
        	diaDaSemanaIsbn = 3;
        } else if (digitoFinalIsbn == 6 || digitoFinalIsbn == 7) {
        	diaDaSemanaIsbn = 4;
        } else {
        	diaDaSemanaIsbn = 5;
        }
		
		int diaDaRequisicao = data.getValue();
		if(diaDaRequisicao == diaDaSemanaIsbn) {
			return true;
		}else {
			return false;
		}
	}
	
//	// estava aqui antes
//	@Transactional
//	public UsuarioDTO insert(UsuarioInserirDTO dto) {
////	public UsuarioDTO insert(UsuarioDTO dto) {
//		Usuario entity = new Usuario();
//		entity.setNome(dto.getNome());
//		entity.setEmail(dto.getEmail());
//		entity.setCpf(dto.getCpf());
//		entity.setDataNascimento(dto.getDataNascimento());
//		entity = repository.save(entity);
//		return new UsuarioDTO(entity);
//	}
	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioAtualizarDTO dto) {
		try {
			Usuario entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity.setCpf(dto.getCpf());
			entity.setDataNascimento(dto.getDataNascimento());
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id nao encontrado " + id);
		}
	}
	
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id nao encontrado " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violacao de integridade");
		}
	}

}
