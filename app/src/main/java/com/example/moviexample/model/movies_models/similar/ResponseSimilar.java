package com.example.moviexample.model.movies_models.similar;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSimilar{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<Similar> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<Similar> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}