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
import android.widget.Toast;

import com.example.moviexample.R;
import com.example.moviexample.adapter.movies_adapter.AdapterListMovie;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.movies_models.movies.ResponseMovies;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcoming extends Fragment {
    @BindView(R.id.rv_upcoming)
    RecyclerView rv_upcoming;
    @BindView(R.id.progress_upcoming)
    ProgressBar progressBar;

    private ApiInterface apiInterface;
    private AdapterListMovie adapter;
    private List<Movies> list;
    private LinearLayoutManager llm;


    public Upcoming() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        setUpUpcoming();
    }

    private void setUpUpcoming() {
        llm = new LinearLayoutManager(getContext());
        rv_upcoming.setLayoutManager(llm);
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        loadDataUpcoming();
    }

    private void loadDataUpcoming() {
        apiInterface.getUpcoming(RetrofitApi.API_KEY,1).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()  && response!=null){
                    progressBar.setVisibility(View.GONE);
                    list = response.body().getResults();
                    adapter = new AdapterListMovie(list, getContext());
                    rv_upcoming.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
