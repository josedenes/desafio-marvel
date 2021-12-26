package br.com.zup.desafio.marvel.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatorsItens {
	
	private String name;
	
	
	public CreatorsItens(@JsonProperty("name") String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
