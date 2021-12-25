package br.com.zup.desafio.marvel.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zup.desafio.marvel.api.response.ComicsResponse;

@FeignClient(url = "${marvel.url}/comics", name = "marvel-url")
public interface MarvelComicsClient {
	
	@GetMapping("/{id}")
	ResponseEntity<ComicsResponse> findComicsPorId(
			@PathVariable Long id,
			@RequestParam String ts,
            @RequestParam String apikey,
            @RequestParam String hash);
}
