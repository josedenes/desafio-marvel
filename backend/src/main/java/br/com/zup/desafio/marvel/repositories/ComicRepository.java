package br.com.zup.desafio.marvel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafio.marvel.entities.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{
	
}
