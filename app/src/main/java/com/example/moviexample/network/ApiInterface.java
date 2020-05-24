package com.example.moviexample.network;

import com.example.moviexample.model.movies_models.credits.ResponseCredits;
import com.example.moviexample.model.movies_models.movies.ResponseMovies;
import com.example.moviexample.model.movies_models.detail.ResponseDetail;
import com.example.moviexample.model.movies_models.similar.ResponseSimilar;
import com.example.moviexample.model.tvshow_models.cast.ResponseCast;
import com.example.moviexample.model.tvshow_models.similar_tv.ResponseSimilarTV;
import com.example.moviexample.model.tvshow_models.tv_detail.ResponseTvDetail;
import com.example.moviexample.model.tvshow_models.tv_model.ResponseTV;
import com.example.moviexample.model.movies_models.videos.ResponseVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Harri Pratomo on 06/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public interface ApiInterface {
    @GET("movie/now_playing")
    Call<ResponseMovies> getNowPlaying(@Query("api_key") String key,@Query("page") int page);

    @GET("movie/upcoming")
    Call<ResponseMovies> getUpcoming(@Query("api_key") String key,@Query("page") int page);

    @GET("movie/top_rated")
    Call<ResponseMovies> getTopRated(@Query("api_key") String key,@Query("page") int page);

    @GET("movie/popular")
    Call<ResponseMovies> getPopular(@Query("api_key") String key);

    @GET("movie/latest")
    Call<ResponseMovies> getLatest(@Query("api_key") String key,@Query("page") int page);

    @GET("movie/{id}")
    Call<ResponseDetail> getDetailMovie(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/credits")
    Call<ResponseCredits> getCredits(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/similar")
    Call<ResponseSimilar> getSimilar(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/recommendations")
    Call<ResponseMovies> getRecomendations(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<ResponseVideos> getVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/movie?api_key="+ RetrofitApi.API_KEY)
    Call<ResponseMovies> getItemSearch(@Query("query") String movie_name);

    //TVSHOW
    @GET("tv/airing_today")
    Call<ResponseTV> getTvAiring(@Query("api_key") String key, @Query("page") int page);

    @GET("tv/on_the_air")
    Call<ResponseTV> getOnTheAir(@Query("api_key") String key, @Query("page") int page);

    @GET("tv/popular")
    Call<ResponseTV> getPopularTV(@Query("api_key") String key, @Query("page") int page);

    @GET("tv/top_rated")
    Call<ResponseTV> getTopRatedTV(@Query("api_key") String key, @Query("page") int page);

    @GET("tv/{id}")
    Call<ResponseTvDetail> getDetailTV(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/credits")
    Call<ResponseCast> getCreditsTV(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/similar")
    Call<ResponseSimilarTV> getSimilarTV(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{id}/recommendations")
    Call<ResponseTV> getRecomendationsTV(@Path("id") int id, @Query("api_key") String apiKey);
}

