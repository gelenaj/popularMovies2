package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Review;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class ReviewResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private List<Review> results = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Review> getResults(){
        return results;
    }
}