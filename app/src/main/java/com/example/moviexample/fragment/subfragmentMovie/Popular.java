package com.example.moviexample.fragment.subfragmentMovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviexample.R;
import com.example.moviexample.adapter.movies_adapter.AdapterListMovie;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.movies_models.movies.ResponseMovies;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Popular extends Fragment {

    @BindView(R.id.rv_popular)
    RecyclerView rv_populars;
    @BindView(R.id.progress_popular)
    ProgressBar progressBar;

    private ApiInterface apiInterface;
    private AdapterListMovie adapterListMovie;
    private List<Movies> list = new ArrayList<>();
    private LinearLayoutManager llm;


    public Popular() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        setUpPopular();
    }

    private void setUpPopular() {
        llm = new LinearLayoutManager(getContext());
        rv_populars.setLayoutManager(llm);
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        loadDataPopular();
    }

    private void loadDataPopular() {
        apiInterface.getPopular(RetrofitApi.API_KEY).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    list = response.body().getResults();
                    adapterListMovie = new AdapterListMovie(list, getContext());
                    rv_populars.setAdapter(adapterListMovie);
                    adapterListMovie.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
               t.getMessage();
            }
        });
    }
}
