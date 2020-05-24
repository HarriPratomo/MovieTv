package com.example.moviexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;

import com.example.moviexample.R;
import com.example.moviexample.adapter.movies_adapter.AdapterListMovie;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.movies_models.movies.ResponseMovies;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{


    @BindView(R.id.search)
    SearchView searchView;

    @BindView(R.id.rv_search)
    RecyclerView r_search;

    @OnClick(R.id.close)
    void closed(){
        this.finish();
    }

    private ApiInterface apiInterface;
    private AdapterListMovie adapter;
    private List<Movies> listMovies = new ArrayList<>();
    private LinearLayoutManager llm;
    private Movies movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        movie = new Movies();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search ....");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loadMovie();
        return false;
    }

    private void loadMovie() {
            apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
            llm = new LinearLayoutManager(this);
            r_search.setLayoutManager(llm);
            String input_movie = searchView.getQuery().toString();

            apiInterface.getItemSearch(input_movie).enqueue(new Callback<ResponseMovies>() {
                @Override
                public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                    if (response.isSuccessful()){
                        listMovies = response.body().getResults();
                        adapter = new AdapterListMovie(listMovies,getApplicationContext());
                        r_search.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ResponseMovies> call, Throwable t) {
                    t.getMessage();
                }
            });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
