package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Movie;

public interface  OnGetMovieCallback {
    void onSuccess(Movie movie);
    void onError();
}
