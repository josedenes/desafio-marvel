package br.com.zup.desafio.marvel.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.zup.desafio.marvel.api.SolicitarComic;
import br.com.zup.desafio.marvel.api.response.ResultsResponse;
import br.com.zup.desafio.marvel.dto.ComicDTO;
import br.com.zup.desafio.marvel.services.ComicService;


@RestController
@RequestMapping(value = "/comics")
public class ComicResource {
	
	@Autowired
	private ComicService service;
	
	@Autowired
	private SolicitarComic solicitarComic;
	
	@GetMapping
	public ResponseEntity<List<ComicDTO>> findAll(){
		List<ComicDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ComicDTO> findById(@PathVariable Long id){
		
		ComicDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	
	@PostMapping
	public ResponseEntity<ComicDTO> insert(@RequestBody ObjectNode objectNode){
		Long comicId = objectNode.get("comicId").asLong();
		Long usuarioId = objectNode.get("usuarioId").asLong();
		
		ComicDTO dto = service.insert(comicId, usuarioId);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getComicId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ComicDTO> update(@PathVariable Long id, @RequestBody ComicDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ComicDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
