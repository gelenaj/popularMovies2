package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Trailer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class TrailerResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("results")
    @Expose
    private List<Trailer> results = null;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<Trailer> getResults() {
        return results;
    }
}
