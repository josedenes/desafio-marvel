package br.com.zup.desafio.marvel.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultsResponse {

	@JsonProperty("id")
	private Integer comicId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("description")
	private String description;
	
	private List<PriceResponse> prices;

	public ResultsResponse(Integer comicId, String title, String isbn, String description, List<PriceResponse> prices) {
		super();
		this.comicId = comicId;
		this.title = title;
		this.isbn = isbn;
		this.description = description;
		this.prices = prices;
	}
	
	public Integer getComicId() {
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
	
	
}
