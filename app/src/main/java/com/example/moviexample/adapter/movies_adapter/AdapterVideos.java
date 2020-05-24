package com.example.moviexample.adapter.movies_adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviexample.R;
import com.example.moviexample.activity.YoutubePlayer;
import com.example.moviexample.network.RetrofitApi;
import com.example.moviexample.model.movies_models.videos.Videos;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Harri Pratomo on 09/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.ViewHolder> {
    private List<Videos> videosList;
    private Context context;

    public AdapterVideos(List<Videos> videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterVideos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_videos, parent, false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVideos.ViewHolder holder, int position) {
        Videos videos = videosList.get(position);
        holder.name.setText(videos.getName());
        String thumbnail = RetrofitApi.TRAILER_THUMBNAIL_IMAGE_URL+videos.getKey()+ "/hqdefault.jpg";
        Picasso.get().load(thumbnail).placeholder(R.drawable.no_image).error(R.drawable.no_image).resize(300,200).into(holder.videoImage);

        holder.playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, YoutubePlayer.class);
                intent.putExtra("key",videos.getKey());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageVideos)
        ImageView videoImage;
        @BindView(R.id.play)
        ImageButton playing;
        @BindView(R.id.nameVideo)
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
