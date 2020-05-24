package com.example.moviexample.adapter.movies_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.credits.CastItem;
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
public class AdapterCast extends RecyclerView.Adapter<AdapterCast.ViewHolder> {

    private List<CastItem> itemList;
    private Context context;

    public AdapterCast(List<CastItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCast.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCast.ViewHolder holder, int position) {
        CastItem item = itemList.get(position);
        String poster = RetrofitApi.POSTER_URL+item.getProfilePath();
        holder.name.setText(item.getName());
        holder.become.setText(item.getCharacter());
        Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().into(holder.castImage);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageCast)
        ImageView castImage;
        @BindView(R.id.nameActor)
        TextView name;
        @BindView(R.id.becomeActor)
        TextView become;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
