package com.example.moviexample.model.tvshow_models.similar_tv;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSimilarTV{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<similarTV> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<similarTV> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}