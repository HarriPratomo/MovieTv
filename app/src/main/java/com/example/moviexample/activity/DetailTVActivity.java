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

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.adapter.tv_adapter.AdapterCastTV;
import com.example.moviexample.adapter.tv_adapter.AdapterRecomendationTV;
import com.example.moviexample.adapter.tv_adapter.AdapterSimilarTV;
import com.example.moviexample.database.movie_database.MovieHelper;
import com.example.moviexample.database.tv_database.TvHelper;
import com.example.moviexample.model.movies_models.credits.CastItem;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.tvshow_models.cast.CastItemTV;
import com.example.moviexample.model.tvshow_models.cast.ResponseCast;
import com.example.moviexample.model.tvshow_models.similar_tv.ResponseSimilarTV;
import com.example.moviexample.model.tvshow_models.similar_tv.similarTV;
import com.example.moviexample.model.tvshow_models.tv_detail.GenreTV;
import com.example.moviexample.model.tvshow_models.tv_detail.ResponseTvDetail;
import com.example.moviexample.model.tvshow_models.tv_model.ResponseTV;
import com.example.moviexample.model.tvshow_models.tv_model.TV;
import com.example.moviexample.network.ApiInterface;
import com.example.moviexample.network.RetrofitApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailTVActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    int id,linecount;
    public static final String EXTRA_TV = "extraTV";
    private TV item;
    private TvHelper tvHelper;
    private List<CastItemTV> castItemTVList = new ArrayList<>();
    private List<similarTV> similarTVList = new ArrayList<>();
    private List<TV> tvList = new ArrayList<>();
    private LinearLayoutManager llm;
    private AdapterCastTV adapterCastTV;
    private AdapterSimilarTV adapterSimilarTV;
    private AdapterRecomendationTV adapterRecomendation;
    private String key = RetrofitApi.API_KEY;
    @BindView(R.id.imageTV_Detail) ImageView imageTV;
    @BindView(R.id.imagePoster_Detail) ImageView imagePoster;
    @BindView(R.id.nameTVDetail) TextView titleTV;
    @BindView(R.id.ratingBarTV) RatingBar rating;
    @BindView(R.id.dateTV) TextView date;
    @BindView(R.id.descTVDetail) TextView description;
    @BindView(R.id.genreTV) TextView genre;
    @BindView(R.id.txtMore) TextView more;
    @BindView(R.id.emptyText) TextView emptySimilar;
    @BindView(R.id.emptyTexttv) TextView emptyrecomendation;
    @BindView(R.id.rv_cast_tv)
    RecyclerView r_cast_tv;
    @BindView(R.id.rv_similar_tv)
    RecyclerView r_similar_tv;
    @BindView(R.id.rv_recomend_tv)
    RecyclerView r_recomendation_tv;
    @BindView(R.id.favBtnTV)
    FloatingActionButton favorite;


    @OnClick(R.id.favBtnTV)
    void clicked() {
        //todo here
    }

    @OnClick(R.id.backBtnTV)
    void click() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t_v);
        id = getIntent().getIntExtra("id",0);
        item = getIntent().getParcelableExtra(EXTRA_TV);
        tvHelper = TvHelper.getInstance(this);
        tvHelper.open();
        apiInterface = RetrofitApi.getRetrofit().create(ApiInterface.class);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        getDetailData();
        getDetailCast();
        getSimilarTV();
        getRecomendationTV();
    }

    private void getRecomendationTV() {
        llm = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        r_recomendation_tv.setLayoutManager(llm);
        apiInterface.getRecomendationsTV(id,key).enqueue(new Callback<ResponseTV>() {
            @Override
            public void onResponse(Call<ResponseTV> call, Response<ResponseTV> response) {
                if(response.isSuccessful()){
                    tvList = response.body().getResults();
                    if (tvList.isEmpty()){
                        emptyrecomendation.setVisibility(View.VISIBLE);
                        r_recomendation_tv.setVisibility(View.GONE);
                    }else {
                        emptyrecomendation.setVisibility(View.GONE);
                        r_recomendation_tv.setVisibility(View.VISIBLE);
                        tvList = response.body().getResults();
                        adapterRecomendation = new AdapterRecomendationTV(tvList, getApplicationContext());
                        r_recomendation_tv.setAdapter(adapterRecomendation);
                        adapterRecomendation.notifyDataSetChanged();
                    }
                }
                else {
                    Snackbar.make(findViewById(R.id.detailTV), "load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTV> call, Throwable t) {

            }
        });
    }

    private void getSimilarTV() {
        llm = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        r_similar_tv.setLayoutManager(llm);
        apiInterface.getSimilarTV(id,key).enqueue(new Callback<ResponseSimilarTV>() {
            @Override
            public void onResponse(Call<ResponseSimilarTV> call, Response<ResponseSimilarTV> response) {
                if(response.isSuccessful()){
                    similarTVList = response.body().getResults();
                    if (similarTVList.isEmpty()){
                        emptySimilar.setVisibility(View.VISIBLE);
                        r_similar_tv.setVisibility(View.GONE);
                    }else {
                        emptySimilar.setVisibility(View.GONE);
                        r_similar_tv.setVisibility(View.VISIBLE);
                        similarTVList = response.body().getResults();
                        adapterSimilarTV = new AdapterSimilarTV(similarTVList, getApplicationContext());
                        r_similar_tv.setAdapter(adapterSimilarTV);
                        adapterSimilarTV.notifyDataSetChanged();
                    }
                }
                else {
                    Snackbar.make(findViewById(R.id.detailTV), "load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSimilarTV> call, Throwable t) {

            }
        });
    }

    private void getDetailCast() {
        llm = new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false);
        r_cast_tv.setLayoutManager(llm);
        apiInterface.getCreditsTV(id,key).enqueue(new Callback<ResponseCast>() {
            @Override
            public void onResponse(Call<ResponseCast> call, Response<ResponseCast> response) {
                if (response.isSuccessful()){
                    castItemTVList = response.body().getCast();
                    adapterCastTV = new AdapterCastTV(castItemTVList,getApplicationContext());
                    r_cast_tv.setAdapter(adapterCastTV);
                    adapterCastTV.notifyDataSetChanged();
                }
                else {
                    Snackbar.make(findViewById(R.id.detailTV), "load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCast> call, Throwable t) {
                t.getMessage();
            }
        });

    }

    private void getDetailData() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait ...");
        dialog.show();
        apiInterface.getDetailTV(id,key).enqueue(new Callback<ResponseTvDetail>() {
            @Override
            public void onResponse(Call<ResponseTvDetail> call, Response<ResponseTvDetail> response) {
                if (response.isSuccessful()){
                    //load.setVisibility(View.GONE);
                    dialog.dismiss();
                    ResponseTvDetail item = response.body();
                    getDetail(item);
                }else {
                    Snackbar.make(findViewById(R.id.detailTV), "load data failed !", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTvDetail> call, Throwable t) {

            }
        });
    }

    private void getDetail(ResponseTvDetail item) {
        getGenre(item);
        getDate(item);
        String poster = RetrofitApi.POSTER_URL+item.getPosterPath();
        double vote = item.getVoteAverage();
        double rate = vote * 10.0;
        rating.setRating((float) ((rate * 5) / 100));
        description.setText(item.getOverview());
        titleTV.setText(item.getName());
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(imageTV);
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(imagePoster);
        description.post(new Runnable() {
            @Override
            public void run() {
                linecount = description.getLineCount();
            }
        });
    }
    private void getGenre(ResponseTvDetail responseDetail) {
        ArrayList<GenreTV> genres = (ArrayList<GenreTV>) responseDetail.getGenres();
        for (int i = 0; i < genres.size(); i++) {
            if (i < genres.size() - 1)
                genre.append(genres.get(i).getName() + " , ");
            else
                genre.append(genres.get(i).getName());
        }
    }
    private void getDate(ResponseTvDetail detail) {
        String date_release = detail.getFirstAirDate();
        SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputformat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        Date datess = null;
        String str = null;
        try {
            datess = inputformat.parse(date_release);
            str = outputformat.format(datess);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date.setText(str);
    }
    @OnClick(R.id.txtMore)
    void more() {
        if (description.getMaxLines() <= 2) {
            more.setText("Less");
            description.setMaxLines(linecount);
        } else {
            more.setText("...More");
            description.setMaxLines(2);
        }
    }
    @OnClick(R.id.favBtnTV)
    void clickFav() {
        if (!tvHelper.isExist(this.item.getId())) {
            favorite.setImageResource(R.drawable.favorites);
            addToFavorite();
        } else {
            favorite.setImageResource(R.drawable.unfavorites);
            removeFromFavorite();
        }
    }

    private void addToFavorite() {
        long result = tvHelper.insert(this.item);
        if (result > 0)
            Snackbar.make(findViewById(R.id.detailTV), R.string.add_favorite, Snackbar.LENGTH_LONG).show();

        else
            Snackbar.make(findViewById(R.id.detailTV), R.string.fail_favorite, Snackbar.LENGTH_LONG).show();
    }

    private void removeFromFavorite() {
        int result = tvHelper.delete(item.getId());
        if (result > 0) {
            Snackbar.make(findViewById(R.id.detailTV), R.string.remove_favorite, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(findViewById(R.id.detailTV), R.string.fail_remove, Snackbar.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (tvHelper.isExist(item.getId())) {
            favorite.setImageResource(R.drawable.favorites);
        }
    }

}
