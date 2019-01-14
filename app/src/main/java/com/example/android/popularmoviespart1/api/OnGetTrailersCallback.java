package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Trailer;

import java.util.List;

public interface OnGetTrailersCallback {
    void onSuccess(List<Trailer> trailers);
    void onError();
}
