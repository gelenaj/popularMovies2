package com.example.android.popularmoviespart1.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    LiveData<List<Movie>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewFavMovie(Movie movie);

    @Query("SELECT * FROM movie WHERE id LIKE :id")
    Movie getMovieById(int id);

    @Query("SELECT * FROM movie WHERE title LIKE :title")
    Movie getMovieByTitle(String title);


}
