package com.example.moviexample.model.tvshow_models.tv_model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class TV implements Parcelable {

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

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	public void setFirstAirDate(String firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public void setOriginCountry(List<String> originCountry) {
		this.originCountry = originCountry;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public TV(Parcel in) {
		firstAirDate = in.readString();
		overview = in.readString();
		originalLanguage = in.readString();
		posterPath = in.readString();
		originCountry = in.createStringArrayList();
		backdropPath = in.readString();
		originalName = in.readString();
		popularity = in.readDouble();
		voteAverage = in.readDouble();
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

    public TV() {

    }

	public String getFirstAirDate() {
		return firstAirDate;
	}

	public String getOverview() {
		return overview;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public List<String> getOriginCountry() {
		return originCountry;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getOriginalName() {
		return originalName;
	}

	public double getPopularity() {
		return popularity;
	}

	public double getVoteAverage() {
		return voteAverage;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getVoteCount() {
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
		dest.writeDouble(popularity);
		dest.writeDouble(voteAverage);
		dest.writeString(name);
		dest.writeInt(id);
		dest.writeInt(voteCount);
	}

	public TV(JSONObject jObject, String[] keys) {
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