package com.example.moviexample.adapter.tv_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.model.movies_models.credits.CastItem;
import com.example.moviexample.model.tvshow_models.cast.CastItemTV;
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
public class AdapterCastTV extends RecyclerView.Adapter<AdapterCastTV.ViewHolder> {

    private List<CastItemTV> listCast;
    private Context context;

    public AdapterCastTV(List<CastItemTV> listCast, Context context) {
        this.listCast = listCast;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cast_tv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CastItemTV castItem = listCast.get(position);
        holder.name.setText(castItem.getName());
        holder.character.setText(castItem.getCharacter());
        String poster = RetrofitApi.POSTER_URL+castItem.getProfilePath();
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.castImage);
    }

    @Override
    public int getItemCount() {
        return listCast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageCastTV)
        ImageView castImage;
        @BindView(R.id.nameCastTv)
        TextView name;
        @BindView(R.id.carachterTVCast)
        TextView character;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
