package com.example.moviexample.adapter.movies_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.moviexample.activity.DetailMovie;
import com.example.moviexample.R;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 07/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class SliderAdapterNowPlaying extends RecyclerView.Adapter<SliderAdapterNowPlaying.ViewHolder>{
    private Context context;
    private List<Movies> list;

    public SliderAdapterNowPlaying(Context context, List<Movies> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nowplaying, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movies movie = list.get(position);
        double average = movie.getVoteAverage();
        viewHolder.vote.setText(Double.toString(average));
        String poster = RetrofitApi.POSTER_URL + movie.getPosterPath();
        Picasso.get().load(poster)
                .placeholder(R.drawable.placeholder_errors)
                .error(R.drawable.placeholder_errors)
                .fit()
                .into(viewHolder.image);

        viewHolder.genres.setText(movie.getOverview());
        viewHolder.name.setText(movie.getTitle());
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("id",movie.getId());
                intent.putExtra(DetailMovie.EXTRA_ITEM,movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageMovie) ImageView image;
        @BindView(R.id.card_rows) CardView card;
        @BindView(R.id.vote) TextView vote;
        @BindView(R.id.genre) TextView genres;
        @BindView(R.id.names) TextView name;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}