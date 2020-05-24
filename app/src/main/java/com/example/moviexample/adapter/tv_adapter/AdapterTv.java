package com.example.moviexample.adapter.tv_adapter;

import android.annotation.SuppressLint;
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
import com.example.moviexample.activity.DetailTVActivity;
import com.example.moviexample.model.tvshow_models.tv_model.TV;
import com.example.moviexample.network.RetrofitApi;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 10/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterTv extends RecyclerView.Adapter<AdapterTv.ViewHolder> {

    private List<TV> listTV;
    private Context context;

    public AdapterTv(List<TV> listTV, Context context) {
        this.listTV = listTV;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTv.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tv, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterTv.ViewHolder holder, int position) {
        TV item = listTV.get(position);
        holder.vote.setText(Double.toString(item.getVoteAverage()));
        holder.name.setText(item.getName());
        holder.desc.setText(item.getOverview());
        String poster = RetrofitApi.POSTER_URL + item.getPosterPath();
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.image);
        double votes = item.getVoteAverage();
        double rate = votes * 10.0;
        holder.rating.setRating((float) ((rate * 5) / 100));
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTVActivity.class);
                intent.putExtra("id",item.getId());
                intent.putExtra(DetailTVActivity.EXTRA_TV, item);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != listTV ? listTV.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageTV)
        ImageView image;
        @BindView(R.id.ratingBarTV)
        RatingBar rating;
        @BindView(R.id.voteTv)
        TextView vote;
        @BindView(R.id.nameTV)
        TextView name;
        @BindView(R.id.descTV)
        TextView desc;
        @BindView(R.id.clickTV)
        RelativeLayout click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
