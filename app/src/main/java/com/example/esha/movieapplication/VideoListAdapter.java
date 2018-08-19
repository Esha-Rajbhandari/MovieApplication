package com.example.esha.movieapplication;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.esha.movieapplication.Activities.FullMovieActivity;
import com.example.esha.movieapplication.rest.VideoResults;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private Context context;
    private ArrayList<VideoResults> trailerList;
    private int movieId;

    public VideoListAdapter(FullMovieActivity fullMovie, ArrayList<VideoResults> trailerList, int movieId) {
        this.context = fullMovie;
        this.trailerList = trailerList;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list, parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(layoutView);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.movieTrailer.setVideoURI(Uri.parse("http://api.themoviedb.org/3/movie/" + movieId + "/videos?api_key=" + BuildConfig.TMDBMOVIEAPIKEY));
        holder.movieTrailer.start();
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        private VideoView movieTrailer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTrailer = itemView.findViewById(R.id.movie_trailer);
        }
    }
}
