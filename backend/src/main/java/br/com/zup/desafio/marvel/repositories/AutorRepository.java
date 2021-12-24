package br.com.zup.desafio.marvel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.desafio.marvel.entities.Autor;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
	
}
