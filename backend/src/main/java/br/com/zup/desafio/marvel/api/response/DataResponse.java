package br.com.zup.desafio.marvel.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataResponse {
	
	@JsonProperty("results")
	private List<ResultsResponse> results;
	
	public DataResponse(@JsonProperty("results") List<ResultsResponse> results) {
		this.results = results;
	}
	
	public DataResponse() {
		
	}
	
	public ResultsResponse getResult() {
		return results.get(0);
	}

}
