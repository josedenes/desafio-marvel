package br.com.zup.desafio.marvel.api.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatorsResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<CreatorsItens> items;
	
	public CreatorsResponse(@JsonProperty("items") List<CreatorsItens> items) {
		this.items = items;
	}
	
	public List<CreatorsItens> getItens() {
		return items;
	}
	
}
