package br.com.zup.desafio.marvel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafio.marvel.entities.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{
	
	List<Comic> findByUsuarioId(Long usuarioId);
}
