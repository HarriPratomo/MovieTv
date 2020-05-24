package com.example.moviexample.adapter.tv_adapter;

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
import com.example.moviexample.activity.DetailTVActivity;
import com.example.moviexample.model.tvshow_models.similar_tv.similarTV;
import com.example.moviexample.model.tvshow_models.tv_model.TV;
import com.example.moviexample.network.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 11/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterRecomendationTV extends RecyclerView.Adapter<AdapterRecomendationTV.ViewHolder> {
    private List<TV> tvList;
    private Context context;

    public AdapterRecomendationTV(List<TV> tvList, Context context) {
        this.tvList = tvList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterRecomendationTV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_similarmovies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecomendationTV.ViewHolder holder, int position) {
        TV item = tvList.get(position);
        String poster = RetrofitApi.POSTER_URL + item.getPosterPath();
        holder.vote_similar.setText(Double.toString(item.getVoteAverage()));
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.image);
        double vote = item.getVoteAverage();
        double rate = vote * 10.0;
        holder.rating.setRating((float) ((rate * 5) / 100));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context, DetailTVActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra(DetailTVActivity.EXTRA_TV, item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvList == null ? 0 : tvList.size();
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

