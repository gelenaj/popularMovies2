package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;

public interface OnGetMoviesCallback {
    void onSuccess(List<Movie> movies);
    void onError();
}
