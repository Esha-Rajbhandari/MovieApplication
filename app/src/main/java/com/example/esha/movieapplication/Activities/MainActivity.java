package com.example.esha.movieapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.esha.movieapplication.rest.Genre;
import com.example.esha.movieapplication.rest.MovieListing;
import com.example.esha.movieapplication.rest.Result;
import com.example.esha.movieapplication.BuildConfig;
import com.example.esha.movieapplication.MovieListAdapter;
import com.example.esha.movieapplication.R;
import com.example.esha.movieapplication.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvMovieList;
    private MovieListAdapter rcAdapter;
    private ArrayList<Result> upcomingMovieList = new ArrayList<>();
    private ArrayList<Genre> genre = new ArrayList<>();
    private ProgressBar progressBar;
    private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovieList = findViewById(R.id.rv_movie_list);
        rvMovieList.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progress_bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rvMovieList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        rvMovieList.setLayoutManager(linearLayoutManager);

        getMovieList();

        rcAdapter = new MovieListAdapter(MainActivity.this, upcomingMovieList, genre);
        rvMovieList.setAdapter(rcAdapter);
    }

    /**
     * gets the information of upcoming movies.
     * passes the information to full movie activity.
     */
    public void getMovieList() {
        RetrofitManager.getInstance().getUpcomingMovieList(BuildConfig.TMDBMOVIEAPIKEY, new Callback<MovieListing>() {
            @Override
            public void onResponse(Call<MovieListing> call, final Response<MovieListing> response) {
                if (response.code() == 200) {
                    upcomingMovieList.addAll(response.body().getResults());
                    rcAdapter.notifyDataSetChanged();
                    viewVisibility(true);
                    rcAdapter.setClickListener(new MovieListAdapter.MovieItemClickListener() {
                        @Override
                        public void onClick(Result result) {
                            Intent fullMovieIntent = new Intent(MainActivity.this, FullMovieActivity.class);
                            fullMovieIntent.putExtra("result", result);
                            startActivity(fullMovieIntent);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    viewVisibility(false);
                }
            }

            @Override
            public void onFailure(Call<MovieListing> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                viewVisibility(false);
            }
        });
    }

    /**
     * sets the visibilty of the progess bar and movie lists
     * @param dataVisible
     */
    private void viewVisibility(boolean dataVisible) {
        progressBar.setVisibility(dataVisible ? View.GONE : View.VISIBLE);
        rvMovieList.setVisibility(dataVisible ? View.VISIBLE : View.GONE);
    }
}
//jsonschema2pojo
