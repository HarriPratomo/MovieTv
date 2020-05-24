package com.example.moviexample.model.movies_models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Movies implements Parcelable {

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("original_title")
	private String originalTitle;

	@SerializedName("video")
	private boolean video;

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("id")
	private int id;

	@SerializedName("adult")
	private boolean adult;

	@SerializedName("vote_count")
	private int voteCount;

	public Movies(Parcel in) {
		overview = in.readString();
		originalLanguage = in.readString();
		originalTitle = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		releaseDate = in.readString();
		popularity = in.readDouble();
		voteAverage = in.readDouble();
		id = in.readInt();
		adult = in.readByte() != 0;
		voteCount = in.readInt();
	}

	public static final Creator<Movies> CREATOR = new Creator<Movies>() {
		@Override
		public Movies createFromParcel(Parcel in) {
			return new Movies(in);
		}

		@Override
		public Movies[] newArray(int size) {
			return new Movies[size];
		}
	};

	public Movies(JSONObject jObject, String[] keys) {
		try {
			id = jObject.getInt(keys[0]);
			title = jObject.getString(keys[1]);
			posterPath = jObject.getString(keys[2]);
			overview = jObject.getString(keys[3]);
			voteAverage = jObject.getDouble(keys[4]);
			popularity = jObject.getDouble(keys[5]);
			releaseDate = jObject.getString(keys[6]);
			originalLanguage = jObject.getString(keys[7]);
			backdropPath = jObject.getString(keys[8]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Movies() {

	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public void setVideo(boolean video) {
		this.video = video;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGenreIds(List<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public void setBackdropPath(String backdropPath) {
		this.backdropPath = backdropPath;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public boolean isVideo(){
		return video;
	}

	public String getTitle(){
		return title;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public double getPopularity(){
		return popularity;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public int getId(){
		return id;
	}

	public boolean isAdult(){
		return adult;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int i) {
		dest.writeString(overview);
		dest.writeString(originalLanguage);
		dest.writeString(originalTitle);
		dest.writeByte((byte) (video ? 1 : 0));
		dest.writeString(title);
		dest.writeString(posterPath);
		dest.writeString(backdropPath);
		dest.writeString(releaseDate);
		dest.writeDouble(popularity);
		dest.writeDouble(voteAverage);
		dest.writeInt(id);
		dest.writeByte((byte) (adult ? 1 : 0));
		dest.writeInt(voteCount);
	}
}