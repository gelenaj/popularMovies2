package com.example.android.popularmoviespart1.database.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id") int id;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title") String title;

    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = "poster") String poster;

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "synopsis")  String synopsis;

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "rating") String rating;

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "releaseDate") String releaseDate;

    public Movie(){}

    public Movie(String title, String poster, String synopsis, String rating, String releaseDate) {
        this.title = title;
        this.poster = poster;
        this.synopsis = synopsis;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
