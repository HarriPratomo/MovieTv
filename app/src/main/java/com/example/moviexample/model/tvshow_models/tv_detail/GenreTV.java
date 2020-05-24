package com.example.moviexample.model.tvshow_models.tv_detail;

import com.google.gson.annotations.SerializedName;

public class GenreTV {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}