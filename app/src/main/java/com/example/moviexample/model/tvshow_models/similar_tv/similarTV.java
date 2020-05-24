package com.example.moviexample.model.tvshow_models.similar_tv;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.example.moviexample.model.tvshow_models.tv_model.TV;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class similarTV implements Parcelable {

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<String> originCountry;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	public similarTV() {
	}

	public similarTV(Parcel in) {
		firstAirDate = in.readString();
		overview = in.readString();
		originalLanguage = in.readString();
		posterPath = in.readString();
		originCountry = in.createStringArrayList();
		backdropPath = in.readString();
		originalName = in.readString();
		voteAverage = in.readDouble();
		popularity = in.readDouble();
		name = in.readString();
		id = in.readInt();
		voteCount = in.readInt();
	}

	public static final Creator<TV> CREATOR = new Creator<TV>() {
		@Override
		public TV createFromParcel(Parcel in) {
			return new TV(in);
		}

		@Override
		public TV[] newArray(int size) {
			return new TV[size];
		}
	};

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getOriginalName(){
		return originalName;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public double getPopularity(){
		return popularity;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstAirDate);
		dest.writeString(overview);
		dest.writeString(originalLanguage);
		dest.writeString(posterPath);
		dest.writeStringList(originCountry);
		dest.writeString(backdropPath);
		dest.writeString(originalName);
		dest.writeDouble(voteAverage);
		dest.writeDouble(popularity);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeInt(voteCount);
	}
	public similarTV(JSONObject jObject, String[] keys) {
		try {
			id = jObject.getInt(keys[0]);
			name = jObject.getString(keys[1]);
			posterPath = jObject.getString(keys[2]);
			overview = jObject.getString(keys[3]);
			voteAverage = jObject.getDouble(keys[4]);
			popularity = jObject.getDouble(keys[5]);
			firstAirDate = jObject.getString(keys[6]);
			originalLanguage = jObject.getString(keys[7]);
			backdropPath = jObject.getString(keys[8]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}