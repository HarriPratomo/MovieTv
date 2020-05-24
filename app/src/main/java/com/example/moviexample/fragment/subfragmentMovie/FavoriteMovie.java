package com.example.moviexample.fragment.subfragmentMovie;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.moviexample.R;
import com.example.moviexample.activity.DetailMovie;
import com.example.moviexample.adapter.movies_adapter.AdapterListFavoritesMovies;
import com.example.moviexample.database.movie_database.MovieHelper;
import com.example.moviexample.model.movies_models.movies.Movies;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class FavoriteMovie extends Fragment implements AdapterListFavoritesMovies.OnItemClickCallback {
    private RecyclerView rvMovie;
    private MovieHelper movieHelper;

    private AdapterListFavoritesMovies adapter;
    private ArrayList<Movies> listItem;
    @BindView(R.id.nodatafav)
    ImageView nodata;


    public FavoriteMovie() {

    }

    public static com.example.moviexample.fragment.FavoriteFragment newInstance() {
        com.example.moviexample.fragment.FavoriteFragment fragment = new com.example.moviexample.fragment.FavoriteFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_fav);
        movieHelper = MovieHelper.getInstance(getContext());
        listItem = new ArrayList<>();
        adapter = new AdapterListFavoritesMovies(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovie.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        movieHelper.open();
        listItem.clear();
        listItem.addAll(movieHelper.getAllMovies());
        adapter.setData(listItem);
        if (adapter.getItemCount() == 0) {
            rvMovie.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        } else {
            nodata.setVisibility(View.GONE);
            rvMovie.setVisibility(View.VISIBLE);
            adapter.setOnClickCallback(this);
            adapter.notifyDataSetChanged();
            rvMovie.setAdapter(adapter);
            movieHelper.close();
        }
    }

    @Override
    public void onClicked(View v, Movies item, int position) {
        Intent intent = new Intent(getContext(), DetailMovie.class);
        startActivity(intent);
    }
}
