package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Review;

import java.util.List;

public interface OnGetReviewsCallback {
    void onSuccess(List<Review> reviews);
    void onError();
}
