package com.example.android.popularmoviespart1.database.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer  {

    @SerializedName("name")
    @Expose
    private String mTrailerTitle;

    @SerializedName("id")
    @Expose
    private String mTrailerID;

    @SerializedName("key")
    @Expose
    private String mVideoKey;


    public Trailer(String trailerTitle, String trailerID, String videoKey){
        this.mTrailerTitle = trailerTitle;
        this.mTrailerID = trailerID;
        this.mVideoKey = videoKey;
    }

    public String getmTrailerTitle() {
        return mTrailerTitle;
    }

    public void setmTrailerTitle(String mTrailerTitle) {
        this.mTrailerTitle = mTrailerTitle;
    }

    public String getmTrailerID() {
        return mTrailerID;
    }

    public void setmTrailerID(String mTrailerID) {
        this.mTrailerID = mTrailerID;
    }

    public String getmVideoKey() {
        return mVideoKey;
    }

    public void setmVideoKey(String mVideoKey) {
        this.mVideoKey = mVideoKey;
    }
}
