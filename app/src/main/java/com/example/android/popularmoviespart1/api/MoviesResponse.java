package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class MoviesResponse {
    @SerializedName("results")
    @Expose
    private List<Movie> movies;
    public List<Movie> getMovies() {
        return movies;
    }
}
