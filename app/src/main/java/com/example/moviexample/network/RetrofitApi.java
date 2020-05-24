package com.example.moviexample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harri Pratomo on 06/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class RetrofitApi {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "29811cce74c206c29cea1d30bf3a6bb2";
    public static final String POSTER_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780/";
    public static final String TRAILER_THUMBNAIL_IMAGE_URL = "https://img.youtube.com/vi/";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
