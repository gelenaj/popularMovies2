package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MovieApi {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(
            @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<TrailerResponse> getVideos(
            @Path("id") int id,
            @Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Call<Movie> getMovie(
            @Path("movieId") int id,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movieId}/reviews")
    Call<ReviewResponse> getReviews(
            @Path("movieId") int id,
            @Query("api_key") String apiKEy
    );

}
