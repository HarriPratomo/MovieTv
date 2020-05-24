package com.example.moviexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.adapter.movies_adapter.AdapterCast;
import com.example.moviexample.adapter.movies_adapter.AdapterRecomendation;
import com.example.moviexample.adapter.movies_adapter.AdapterSimilar;
import com.example.moviexample.adapter.movies_adapter.AdapterVideos;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.database.movie_database.MovieHelper;
import com.example.moviexample.model.movies_models.credits.CastItem;
import com.example.moviexample.model.movies_models.credits.ResponseCredits;
import com.example.moviexample.model.movies_models.detail.GenresItem;
import com.example.moviexample.model.movies_models.detail.ResponseDetail;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.movies_models.movies.ResponseMovies;
import com.example.moviexample.model.movies_models.similar.ResponseSimilar;
import com.example.moviexample.model.movies_models.similar.Similar;
import com.example.moviexample.model.movies_models.videos.ResponseVideos;
import com.example.moviexample.model.movies_models.videos.Videos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailMovie extends AppCompatActivity {
    int id;
    int linecount;
    public static final String EXTRA_ITEM = "extraItem";
    private Movies item;
    private MovieHelper movieHelper;
    private ApiInterface apiInterface;
    private AdapterCast adapterCast;
    private AdapterVideos adapterVideos;
    private AdapterSimilar adapterSimilar;
    private AdapterRecomendation adapterRecomendation;
    private List<CastItem> listCast = new ArrayList<>();
    private List<Similar> listSimilar = new ArrayList<>();
    private List<Movies> listRecomendation = new ArrayList<>();
    private List<Videos> videosList = new ArrayList<>();
    private LinearLayoutManager llm;
    String key = RetrofitApi.API_KEY;
    @BindView(R.id.emptyText)
    TextView textEmpty;
    @BindView(R.id.txtMore)
    TextView moreText;
    @BindView(R.id.emptyTextRecomendation)
    TextView textEmptyRecomendation;
    @BindView(R.id.progress_detail)
    ProgressBar load;
    @BindView(R.id.rv_cast)
    RecyclerView cast_list;
    @BindView(R.id.rv_video)
    RecyclerView r_video;
    @BindView(R.id.rv_recomendation)
    RecyclerView recomend_list;
    @BindView(R.id.rv_similar)
    RecyclerView similar_list;
    @BindView(R.id.backdroppathDetail)
    ImageView backdroppath;
    @BindView(R.id.posterpath)
    ImageView poster;
    @BindView(R.id.titleDetail)
    TextView title;
    @BindView(R.id.date_releaseDetail)
    TextView date_release;
    @BindView(R.id.durationDetail)
    TextView duration;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.genre_movieDetail)
    TextView genres;
    @BindView(R.id.descDetail)
    TextView description;
    @BindView(R.id.favBtn)
    FloatingActionButton favorites;

    @OnClick(R.id.backBtn)
    void click() {
        finish();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        item = getIntent().getParcelableExtra(EXTRA_ITEM);
        movieHelper = MovieHelper.getInstance(this);
        movieHelper.open();
        initUI();
    }

    private void initUI() {
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        initDetailData();
        initDetailCast();
        initDetailSimilarMovie();
        initDetailRecomendation();
        initVideosDetail();
    }

    private void initVideosDetail() {
        llm = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        r_video.setLayoutManager(llm);
        apiInterface.getVideos(id, key).enqueue(new Callback<ResponseVideos>() {
            @Override
            public void onResponse(Call<ResponseVideos> call, Response<ResponseVideos> response) {
                if (response.isSuccessful()) {
                    videosList = response.body().getResults();
                    adapterVideos = new AdapterVideos(videosList, getApplicationContext());
                    r_video.setAdapter(adapterVideos);
                    adapterVideos.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseVideos> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void initDetailRecomendation() {
        llm = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recomend_list.setLayoutManager(llm);
        apiInterface.getRecomendations(id, RetrofitApi.API_KEY).enqueue(new Callback<ResponseMovies>() {
            @Override
            public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                if (response.isSuccessful()) {
                    listRecomendation = response.body().getResults();
                    if (listRecomendation.isEmpty()) {
                        recomend_list.setVisibility(View.GONE);
                        textEmptyRecomendation.setVisibility(View.VISIBLE);
                    } else {
                        recomend_list.setVisibility(View.VISIBLE);
                        textEmptyRecomendation.setVisibility(View.GONE);
                        listRecomendation = response.body().getResults();
                        adapterRecomendation = new AdapterRecomendation(listRecomendation, getApplicationContext());
                        recomend_list.setAdapter(adapterRecomendation);
                        adapterRecomendation.notifyDataSetChanged();
                    }
                } else {
                    Snackbar.make(findViewById(R.id.detailmovie), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovies> call, Throwable t) {
                t.getMessage();
            }
        });

    }

    private void initDetailSimilarMovie() {

        llm = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        similar_list.setLayoutManager(llm);
        apiInterface.getSimilar(id, RetrofitApi.API_KEY).enqueue(new Callback<ResponseSimilar>() {
            @Override
            public void onResponse(Call<ResponseSimilar> call, Response<ResponseSimilar> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResults().isEmpty()) {
                        textEmpty.setVisibility(View.VISIBLE);
                        similar_list.setVisibility(View.GONE);
                    } else {
                        listSimilar = response.body().getResults();
                        adapterSimilar = new AdapterSimilar(listSimilar, getApplicationContext());
                        similar_list.setAdapter(adapterSimilar);
                        adapterSimilar.notifyDataSetChanged();
                        textEmpty.setVisibility(View.GONE);
                        similar_list.setVisibility(View.VISIBLE);
                    }
                } else {
                    Snackbar.make(findViewById(R.id.detailmovie), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSimilar> call, Throwable t) {
                t.getMessage();
            }
        });

    }

    private void initDetailCast() {
        llm = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        cast_list.setLayoutManager(llm);
        apiInterface.getCredits(id, RetrofitApi.API_KEY).enqueue(new Callback<ResponseCredits>() {
            @Override
            public void onResponse(Call<ResponseCredits> call, Response<ResponseCredits> response) {
                if (response.isSuccessful()) {
                    listCast = response.body().getCast();
                    adapterCast = new AdapterCast(listCast, getApplicationContext());
                    cast_list.setAdapter(adapterCast);
                    adapterCast.notifyDataSetChanged();
                } else {
                    Snackbar.make(findViewById(R.id.detailmovie), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCredits> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void initDetailData() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("loading ...");
        dialog.show();
        apiInterface.getDetailMovie(id, RetrofitApi.API_KEY).enqueue(new Callback<ResponseDetail>() {
            @Override
            public void onResponse(Call<ResponseDetail> call, Response<ResponseDetail> response) {
                if (response.isSuccessful()) {
                    dialog.dismiss();
                    load.setVisibility(View.GONE);
                    ResponseDetail item = response.body();
                    getDetail(item);
                } else {
                    Snackbar.make(findViewById(R.id.detailmovie), "Load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetail> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getDetail(ResponseDetail responseDetail) {
        getDate(responseDetail);
        getGenre(responseDetail);
        String posters = RetrofitApi.POSTER_URL + responseDetail.getPosterPath();
        String backdrop = RetrofitApi.BACKDROP_BASE_URL + responseDetail.getBackdropPath();
        double vote = responseDetail.getVoteAverage();
        double rating = vote * 10.0;
        ratingBar.setRating((float) ((rating * 5) / 100));
        title.setText(responseDetail.getOriginalTitle());
        description.setText(responseDetail.getOverview());
        duration.setText(responseDetail.getRuntime() / 60 + " hrs " + responseDetail.getRuntime() % 60 + " mins");
        Picasso.get().load(posters).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(poster);
        Picasso.get().load(backdrop).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(backdroppath);
        description.post(new Runnable() {
            @Override
            public void run() {
                linecount = description.getLineCount();
            }
        });
    }

    @OnClick(R.id.txtMore)
    void more() {
        if (description.getMaxLines() <= 2) {
            moreText.setText("Less");
            description.setMaxLines(linecount);
        } else {
            moreText.setText("...More");
            description.setMaxLines(2);
        }
    }


    private void getGenre(ResponseDetail responseDetail) {
        ArrayList<GenresItem> genre = (ArrayList<GenresItem>) responseDetail.getGenres();
        for (int i = 0; i < genre.size(); i++) {
            if (i < genre.size() - 1)
                genres.append(genre.get(i).getName() + ", ");
            else
                genres.append(genre.get(i).getName());
        }
    }

    private void getDate(ResponseDetail detail) {
        String date = detail.getReleaseDate();
        SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputformat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        Date datess = null;
        String str = null;
        try {
            datess = inputformat.parse(date);
            str = outputformat.format(datess);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date_release.setText(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    @OnClick(R.id.favBtn)
    void clickFav() {
        if (!movieHelper.isExist(this.item.getId())) {
            favorites.setImageResource(R.drawable.favorites);
            addToFavorite();
        } else {
            favorites.setImageResource(R.drawable.unfavorites);
            removeFromFavorite();
        }
    }

    private void addToFavorite() {
        long result = movieHelper.insert(this.item);
        if (result > 0)
            Snackbar.make(findViewById(R.id.detailmovie), R.string.add_favorite, Snackbar.LENGTH_LONG).show();

        else
            Snackbar.make(findViewById(R.id.detailmovie), R.string.fail_favorite, Snackbar.LENGTH_LONG).show();
    }

    private void removeFromFavorite() {
        int result = movieHelper.delete(item.getId());
        if (result > 0) {
            Snackbar.make(findViewById(R.id.detailmovie), R.string.remove_favorite, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(findViewById(R.id.detailmovie), R.string.fail_remove, Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (movieHelper.isExist(item.getId())) {
            favorites.setImageResource(R.drawable.favorites);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
