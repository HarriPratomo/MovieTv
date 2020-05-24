package com.example.moviexample.model.tvshow_models.cast;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseCast{

	@SerializedName("cast")
	private List<CastItemTV> cast;

	@SerializedName("id")
	private int id;

	@SerializedName("crew")
	private List<Object> crew;

	public List<CastItemTV> getCast(){
		return cast;
	}

	public int getId(){
		return id;
	}

	public List<Object> getCrew(){
		return crew;
	}
}