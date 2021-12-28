package br.com.zup.desafio.marvel.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.desafio.marvel.api.SolicitarComic;
import br.com.zup.desafio.marvel.api.response.ResultsResponse;
import br.com.zup.desafio.marvel.dto.ComicDTO;
import br.com.zup.desafio.marvel.entities.Comic;
import br.com.zup.desafio.marvel.entities.Usuario;
import br.com.zup.desafio.marvel.repositories.ComicRepository;
import br.com.zup.desafio.marvel.repositories.UsuarioRepository;
import br.com.zup.desafio.marvel.services.exceptions.DatabaseException;
import br.com.zup.desafio.marvel.services.exceptions.ResourceNotFoundException;
import feign.FeignException;

@Service
public class ComicService {
	
	@Autowired
	private ComicRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitarComic solicitarComic;
	
	@Transactional(readOnly = true)
	public List<ComicDTO> findAll(){
		List<Comic> list = repository.findAll();
		return list.stream().map(x -> new ComicDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public ComicDTO findById(Long id) {
		Optional<Comic> obj = repository.findById(id); 
		Comic entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade nao encontrada"));
		return new ComicDTO(entity);
	}
	
	
	@Transactional
	public ComicDTO insert(Long comicId, Long usuarioId) {
		Optional<Usuario> obj = usuarioRepository.findById(usuarioId); 
		Usuario entityUsuario = obj.orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrada"));
		
		ResultsResponse resposta;
		
		try {
			resposta = solicitarComic.buscaComicPorId(comicId);
		}
		catch(FeignException e) {
			throw new ResourceNotFoundException("Id da comic nao encontrado: " + comicId);
		}
		
		Comic entity = new Comic();
		entity.setComicId(resposta.getComicId());
		entity.setTitulo(resposta.getTitle());
		entity.setPreco(resposta.getPrice());
		entity.setAutores(resposta.getCreators());
		entity.setIsbn(resposta.getIsbn());
		entity.setDescricao(resposta.getDescription());
		entity.setAplicaDesconto(false);
		entity.setUsuario(entityUsuario);
		entity = repository.save(entity);
		return new ComicDTO(entity);
	}
	
	
//	@Transactional
//	public ComicDTO insert(ComicDTO dto) {
//		Comic entity = new Comic();
//		//acrescentado
//		entity.setComicId(dto.getComicId());
//		entity.setTitulo(dto.getTitulo());
//		entity.setPreco(dto.getPreco());
//		entity.setAutores(dto.getAutores());
//		entity.setIsbn(dto.getIsbn());
//		entity.setDescricao(dto.getDescricao());
//		entity.setAplicaDesconto(dto.getAplicaDesconto());
//		entity = repository.save(entity);
//		return new ComicDTO(entity);
//	}
	
	@Transactional
	public ComicDTO update(Long id, ComicDTO dto) {
		try {
			Comic entity = repository.getOne(id);
			//acrescentado
			entity.setComicId(dto.getComicId());
			entity.setTitulo(dto.getTitulo());
			entity.setPreco(dto.getPreco());
			entity.setAutores(dto.getAutores());
			entity.setIsbn(dto.getIsbn());
			entity.setDescricao(dto.getDescricao());
			entity.setAplicaDesconto(dto.getAplicaDesconto());
			entity = repository.save(entity);
			return new ComicDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id nao encontrado: " + id);
		}
	}
	
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id nao encontrado: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violacao de integridade");
		}
	}
	
	
}
