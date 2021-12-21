package br.com.zup.desafio.marvel.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.marvel.entities.Usuario;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> list = new ArrayList<>();
		list.add(new Usuario(1L, "jose", "jose@gmail.com", "000.000.000.00",Instant.now()));
		return ResponseEntity.ok().body(list);
		
	}

}
