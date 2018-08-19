package com.example.esha.movieapplication.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieListService {
    @GET("movie/upcoming")
    Call<MovieListing> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<MovieVideoList> getTrailer(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewList> getReviews(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<Genre> getGenre(@Path("genreId") int id, @Query("api_key") String apiKey);
}
