package com.example.moviexample.model.movies_models.videos;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseVideos{

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<Videos> results;

	public int getId(){
		return id;
	}

	public List<Videos> getResults(){
		return results;
	}
}