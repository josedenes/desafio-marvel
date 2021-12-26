package br.com.zup.desafio.marvel.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultsResponse {

	@JsonProperty("id")
	private Long comicId;
//	private Integer comicId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("description")
	private String description;

	@JsonProperty("prices")
	private List<PriceResponse> prices;

	@JsonProperty("creators")
	private CreatorsResponse creators;

	public ResultsResponse(Long comicId, String title, String isbn, String description, List<PriceResponse> prices,
			CreatorsResponse creators) {
		super();
		this.comicId = comicId;
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.prices = prices;
		this.creators = creators;
	}

	public Long getComicId() {
		return comicId;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return prices.get(0).getPrice();
	}

//	public CreatorsResponse getCreators() {	
//		return creators;
//	}

	public String getCreators() {
		String autores = "";
		int i = 0;
		for (CreatorsItens item : creators.getItens()) {
			if(i++ == creators.getItens().size() - 1){
				autores = autores.concat(item.getName());
			}else {
				autores = autores.concat(item.getName()+", ");
			}
		}
		
		return autores;
	}

}
