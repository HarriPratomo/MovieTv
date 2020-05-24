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
import com.example.moviexample.activity.DetailTVActivity;
import com.example.moviexample.adapter.movies_adapter.AdapterListFavoritesMovies;
import com.example.moviexample.adapter.tv_adapter.AdapterFavoritesTV;
import com.example.moviexample.database.movie_database.MovieHelper;
import com.example.moviexample.database.tv_database.TvHelper;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.tvshow_models.tv_model.TV;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class FavoriteTV extends Fragment implements AdapterFavoritesTV.OnItemClickCallback {

    private TvHelper tvHelper;
    private AdapterFavoritesTV adapter;
    private ArrayList<TV> listItem;
    @BindView(R.id.nodatafavtv)
    ImageView nodata;
    @BindView(R.id.rv_favTV)
    RecyclerView rvTV;

    public FavoriteTV() {

    }

    public static com.example.moviexample.fragment.FavoriteFragment newInstance() {
        com.example.moviexample.fragment.FavoriteFragment fragment = new com.example.moviexample.fragment.FavoriteFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_favorite_t_v, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvHelper = TvHelper.getInstance(getContext());
        listItem = new ArrayList<>();
        adapter = new AdapterFavoritesTV(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTV.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        tvHelper.open();
        listItem.clear();
        listItem.addAll(tvHelper.getAllTV());
        adapter.setData(listItem);
        if (adapter.getItemCount() == 0) {
            rvTV.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        } else {
            nodata.setVisibility(View.GONE);
            rvTV.setVisibility(View.VISIBLE);
            adapter.setOnClickCallback(this);
            adapter.notifyDataSetChanged();
            rvTV.setAdapter(adapter);
            tvHelper.close();
        }
    }

    @Override
    public void onClicked(View v, Movies item, int position) {
        Intent intent = new Intent(getContext(), DetailTVActivity.class);
        startActivity(intent);
    }
}

