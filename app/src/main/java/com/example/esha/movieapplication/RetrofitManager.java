package com.example.esha.movieapplication;

import com.example.esha.movieapplication.rest.Genre;
import com.example.esha.movieapplication.rest.MovieListService;
import com.example.esha.movieapplication.rest.MovieListing;
import com.example.esha.movieapplication.rest.MovieReviewList;
import com.example.esha.movieapplication.rest.MovieVideoList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public static Retrofit retrofit = null;
    public static MovieListService movieListService = null;
    public static RetrofitManager retrofitManager = null;
    public static String BASE_URL = "https://api.themoviedb.org/3/";

    private RetrofitManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        movieListService = retrofit.create(MovieListService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }

    public static void getUpcomingMovieList(String apiKey, Callback<MovieListing> getMovieListingCallback) {
        Call<MovieListing> getMovieListing = movieListService.getUpcomingMovies(apiKey);
        getMovieListing.enqueue(getMovieListingCallback);
    }

    public static void getMovieTrailer(int movieId, String apiKey, Callback<MovieVideoList> getTrailerCallback) {
        Call<MovieVideoList> getMovieTrailer = movieListService.getTrailer(movieId, apiKey);
        getMovieTrailer.enqueue(getTrailerCallback);
    }

    public static void getMovieReview(int movieId, String apiKey, Callback<MovieReviewList> getReviewCallback) {
        Call<MovieReviewList> getMovieReview = movieListService.getReviews(movieId, apiKey);
        getMovieReview.enqueue(getReviewCallback);
    }

    public static void getMovieGenre(int genreId, String apiKey, Callback<Genre> getGenreCallback){
        Call<Genre> getGenre = movieListService.getGenre(genreId, apiKey);
        getGenre.enqueue(getGenreCallback);
    }
}

//lazy loading

