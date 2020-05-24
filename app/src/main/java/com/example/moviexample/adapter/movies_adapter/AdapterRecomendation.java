package com.example.moviexample.adapter.movies_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.activity.DetailMovie;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.movies.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 08/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterRecomendation extends RecyclerView.Adapter<AdapterRecomendation.ViewHolder>{

    private List<Movies> listMovies;
    private Context context;

    public AdapterRecomendation(List<Movies> listMovies, Context context) {
        this.listMovies = listMovies;
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterRecomendation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_similarmovies, parent, false);
        return new AdapterRecomendation.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecomendation.ViewHolder holder, int position) {
        Movies item = listMovies.get(position);
        String poster = RetrofitApi.POSTER_URL + item.getPosterPath();
        holder.vote_similar.setText(Double.toString(item.getVoteAverage()));
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.image);
        double vote = item.getVoteAverage();
        double rate = vote * 10.0;
        holder.rating.setRating((float) ((rate * 5) / 100));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("id", item.getId());
                intent.putExtra(DetailMovie.EXTRA_ITEM, item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies == null ? 0 : listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageSimilar)
        ImageView image;
        @BindView(R.id.ratingBarSimilar)
        RatingBar rating;
        @BindView(R.id.voteSimilar)
        TextView vote_similar;
        @BindView(R.id.clickSimilar)
        RelativeLayout click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
