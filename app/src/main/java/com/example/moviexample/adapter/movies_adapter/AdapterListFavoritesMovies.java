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
import com.example.moviexample.model.movies_models.movies.Movies;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 09/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterListFavoritesMovies extends RecyclerView.Adapter<AdapterListFavoritesMovies.ViewHolder> {
    private Context context;
    private ArrayList<Movies> listItem;
    private OnItemClickCallback onClickCallback;

    public AdapterListFavoritesMovies(Context context) {
        this.context = context;
        listItem = new ArrayList<>();
    }

    public AdapterListFavoritesMovies(Context context, ArrayList<Movies> listItem) {
        this.context = context;
        this.listItem = listItem;
    }


    public void setData(ArrayList<Movies> listItem) {
        this.listItem.clear();
        this.listItem.addAll(listItem);
        notifyDataSetChanged();
    }

    public void setOnClickCallback(OnItemClickCallback onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    public void removeItem(int position) {
        this.listItem.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItem.size());
    }

    @NonNull
    @Override
    public AdapterListFavoritesMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_favorites, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListFavoritesMovies.ViewHolder holder, int position) {
        Movies movie = listItem.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + listItem.get(position).getPosterPath()).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.image);
        double vote = movie.getVoteAverage();
        double rate = vote * 10.0;
        holder.rating.setRating((float) ((rate * 5) / 100));
        holder.vote_fav.setText(Double.toString(movie.getVoteAverage()));
        holder.card.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        holder.card_images.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra("id", movie.getId());
                intent.putExtra(DetailMovie.EXTRA_ITEM, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageFav)
        ImageView image;
        @BindView(R.id.titleFav)
        TextView title;
        @BindView(R.id.descFav)
        TextView description;
        @BindView(R.id.ratingBarFavorites)
        RatingBar rating;
        @BindView(R.id.voteFavorites)
        TextView vote_fav;
        @BindView(R.id.card_row_fav)
        CardView card;
        @BindView(R.id.cardlines)
        CardView card_images;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickCallback {
        void onClicked(View v, Movies item, int position);
    }
}

