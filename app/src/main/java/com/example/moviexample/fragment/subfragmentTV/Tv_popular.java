package com.example.moviexample.fragment.subfragmentTV;

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
import com.example.moviexample.adapter.tv_adapter.AdapterTv;
import com.example.moviexample.model.tvshow_models.tv_model.ResponseTV;
import com.example.moviexample.model.tvshow_models.tv_model.TV;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tv_popular extends Fragment {

    private ApiInterface apiInterface;
    private AdapterTv adaptertv_popular;
    private LinearLayoutManager llm;
    private List<TV> listTv;

    @BindView(R.id.rv_popular_tv)
    RecyclerView r_popular;
    @BindView(R.id.progress_popular_tv)
    ProgressBar load;

    public Tv_popular() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_tv_popular, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        llm = new LinearLayoutManager(getContext());
        r_popular.setLayoutManager(llm);
        loadData();
    }

    private void loadData() {
        apiInterface.getPopularTV(RetrofitApi.API_KEY, 1).enqueue(new Callback<ResponseTV>() {
            @Override
            public void onResponse(Call<ResponseTV> call, Response<ResponseTV> response) {
                if (response.isSuccessful()) {
                    load.setVisibility(View.GONE);
                    listTv = response.body().getResults();
                    adaptertv_popular = new AdapterTv(listTv, getContext());
                    r_popular.setAdapter(adaptertv_popular);
                    adaptertv_popular.notifyDataSetChanged();
                } else {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(R.id.fragment_tv_airing),
                            "No internet connection ! ", Snackbar.LENGTH_LONG);
                    snackBar.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTV> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}
