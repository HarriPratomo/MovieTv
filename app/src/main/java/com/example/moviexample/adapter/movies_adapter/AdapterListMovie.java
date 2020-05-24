package com.example.moviexample.adapter.movies_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
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
public class AdapterListMovie extends RecyclerView.Adapter<AdapterListMovie.ViewHolder> {
    private List<Movies> list;
    private Context context;

    public AdapterListMovie(List<Movies> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListMovie.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListMovie.ViewHolder holder, int position) {
        Movies item = list.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getOverview());
        double vote = item.getVoteAverage();
        holder.votes.setText(Double.toString(vote));
        String poster = RetrofitApi.POSTER_URL+item.getPosterPath();
        Picasso.get().load(poster)
                .placeholder(R.drawable.placeholder_errors)
                .error(R.drawable.placeholder_errors)
                .fit()
                .into(holder.image);
        double rate = vote * 10.0;
        holder.rating.setRating((float) ((rate * 5) / 100)) ;
        holder.card.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.card_image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.card.setOnClickListener(new View.OnClickListener() {
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
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.images) ImageView image;
        @BindView(R.id.card_row) CardView card;
        @BindView(R.id.cardImage) CardView card_image;
        @BindView(R.id.title_movie) TextView title;
        @BindView(R.id.desc_movie) TextView description;
        @BindView(R.id.ratingBar) RatingBar rating;
        @BindView(R.id.vote_movie) TextView votes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
