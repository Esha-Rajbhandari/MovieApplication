package com.example.esha.movieapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.esha.movieapplication.Activities.MainActivity;
import com.example.esha.movieapplication.rest.Genre;
import com.example.esha.movieapplication.rest.Result;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ListViewHolder> {
    private ArrayList<Result> movieDetails;
    private ArrayList<Genre> genre;
    private Context context;
    private MovieItemClickListener movieItemClickListener;

    public MovieListAdapter(MainActivity mainActivity, ArrayList<Result> upcomingMovieList, ArrayList<Genre> genre) {
        this.context = mainActivity;
        this.movieDetails = upcomingMovieList;
        this.genre = genre;
    }

    public void setClickListener(MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(layoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.tvMovieTitle.setText(movieDetails.get(position).getOriginalTitle());
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w185//" + movieDetails.get(position).getPosterPath())
                .into(holder.imgPoster);
        holder.tvReleaseDate.setText("Release On: " + movieDetails.get(position).getReleaaseDate());

        ArrayList<String> genreString = new ArrayList<>();
        for (int i = 0; i < genre.size(); i++) {
            genreString.add(genre.get(i).getGenreName());
        }
        holder.tvMovieGenre.setText("Genre: " + genreString);
        holder.tvRating.setText("Rating " + movieDetails.get(position).getVoteAverage());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (movieItemClickListener != null) {
                    movieItemClickListener.onClick(movieDetails.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieDetails.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPoster;
        private TextView tvReleaseDate;
        private TextView tvMovieGenre;
        private TextView tvMovieTitle;
        private TextView tvRating;
        private RelativeLayout linearLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvReleaseDate = itemView.findViewById(R.id.release_date);
            tvMovieGenre = itemView.findViewById(R.id.movie_genre);
            tvMovieTitle = itemView.findViewById(R.id.movie_title);
            tvRating = itemView.findViewById(R.id.vote_average);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }

    public interface MovieItemClickListener {
        void onClick(Result result);
    }
}
