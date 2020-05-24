
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
public class TopRated extends Fragment {
    @BindView(R.id.rv_toprated)
    RecyclerView rv_top_rated;
    @BindView(R.id.progress_toprated)
    ProgressBar progressBar;

    private ApiInterface apiInterface;
    private AdapterListMovie adapter;
    private List<Movies> list;
    private LinearLayoutManager llm;


    public TopRated() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_top_rated, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
    }

    private void initUI() {
        setUpTopRated();
    }

    private void setUpTopRated() {
        llm = new LinearLayoutManager(getContext());
        rv_top_rated.setLayoutManager(llm);
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        loadDataTopRated();
    }

    private void loadDataTopRated() {
        apiInterface.getTopRated(RetrofitApi.API_KEY,1).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()  && response!=null){
                    progressBar.setVisibility(View.GONE);
                    list = response.body().getResults();
                    adapter = new AdapterListMovie(list, getContext());
                    rv_top_rated.setAdapter(adapter);
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
