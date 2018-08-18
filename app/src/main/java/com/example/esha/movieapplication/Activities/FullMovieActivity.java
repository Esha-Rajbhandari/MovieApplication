package com.example.esha.movieapplication.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.esha.movieapplication.rest.MovieReviewList;
import com.example.esha.movieapplication.rest.MovieVideoList;
import com.example.esha.movieapplication.rest.Result;
import com.example.esha.movieapplication.rest.ReviewResult;
import com.example.esha.movieapplication.rest.VideoResults;
import com.example.esha.movieapplication.BuildConfig;
import com.example.esha.movieapplication.R;
import com.example.esha.movieapplication.RetrofitManager;
import com.example.esha.movieapplication.ReviewListAdapter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullMovieActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView rvReview;
    private ReviewListAdapter reviewAdapter;
    private ImageView imagePoster;
    private TextView tvTitle;
    private RatingBar ratingBar;
    private TextView tvOverview;
    private ImageView imgPlay;
    private TextView displayVoteAverage;
    private ArrayList<VideoResults> trailerList = new ArrayList<>();
    private ArrayList<ReviewResult> reviewList = new ArrayList<>();
    int movieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_movie);

        tvTitle = findViewById(R.id.tv_title);
        tvOverview = findViewById(R.id.tv_overview);
        ratingBar = findViewById(R.id.rating_bar);
        displayVoteAverage = findViewById(R.id.display_vote_average);
        imgPlay = findViewById(R.id.img_play);
        imagePoster = findViewById(R.id.image_poster);
        rvReview = findViewById(R.id.rv_review);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(FullMovieActivity.this);
        rvReview.setLayoutManager(linearLayoutManager1);

        getData();

        reviewAdapter = new ReviewListAdapter(FullMovieActivity.this, reviewList);
        rvReview.setAdapter(reviewAdapter);
    }

    /**
     * obtains trailer information of a particular movie and its reviews.
     * displays the information in the users UI.
     */

    public void getData() {
        Intent movieIntent = getIntent();
        if (movieIntent == null) {
            return;
        }
        final Result result = movieIntent.getParcelableExtra("result");
        movieId = result.getId();

        RetrofitManager.getInstance().getMovieTrailer(result.getId(), BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieVideoList>() {

            @Override
            public void onResponse(Call<MovieVideoList> call, Response<MovieVideoList> response) {

                if (response.code() == 200) {
                    trailerList.addAll(response.body().getResults());

                } else {
                    Toast.makeText(FullMovieActivity.this, " " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieVideoList> call, Throwable t) {
                Toast.makeText(FullMovieActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());

            }
        });

        RetrofitManager.getInstance().getMovieReview(result.getId(), BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieReviewList>() {
            @Override
            public void onResponse(Call<MovieReviewList> call, Response<MovieReviewList> response) {
                Log.i(TAG, "onResponse: " + result.getId());
                if (response.code() == 200) {

                    reviewList.addAll(response.body().getResults());
                    reviewAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(FullMovieActivity.this, "" + result.getId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieReviewList> call, Throwable t) {
                Toast.makeText(FullMovieActivity.this, "" + result.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(FullMovieActivity.this)
                .load("http://image.tmdb.org/t/p/w185//" + result.getPosterPath())
                .into(imagePoster);
        tvTitle.setText(result.getOriginalTitle() + " (" + result.getReleaaseDate().substring(0, 4) + ")");
        ratingBar.setRating(result.getVoteAverage());
        displayVoteAverage.setText("\t" + result.getVoteAverage() + "/10");
        tvOverview.setText(result.getOverview());
    }

    /**
     * initiates the youtube application to handle the request of the user to play the trailer of a particular movie.
     * @param view
     */
    public void startIntent(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailerList.get(0).getKey()));
        startActivity(intent);
    }
}
