package com.example.android.popularmoviespart1.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppDbRepo {
    private AppDb mDb;

    private static AppDbRepo ourInstance;
    public LiveData<List<Movie>> mMovies;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppDbRepo getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new AppDbRepo(context);
        }

        return ourInstance;
    }


    private AppDbRepo(Context context) {
        mDb = AppDb.getDb(context);
        mMovies =getAllMovies();
    }



    private LiveData<List<Movie>> getAllMovies(){

        return mDb.movieDao().getAll();
    }

    public void insertNewFavMovie(int id, String title, String posterUrl,
                              String synopsis, String rating, String releaseDate) {

       Movie movie = new Movie();
       movie.setId(id);
       movie.setTitle(title);
       movie.setPoster(posterUrl);
       movie.setSynopsis(synopsis);
       movie.setRating(rating);
       movie.setReleaseDate(releaseDate);

        insertNewFavMovie(movie);
    }

    void insertNewFavMovie(final Movie movie){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().insertNewFavMovie(movie);
            }
        });
    }

    public Movie getMovieByTitle(String title) {
        return mDb.movieDao().getMovieByTitle(title);
    }
    public Movie getMovieById(int movieId) {
        return mDb.movieDao().getMovieById(movieId);
    }

    public void deleteFavMovie(final Movie movie) {
    executor.execute(new Runnable() {
        @Override
        public void run() {
            mDb.movieDao().deleteFavMovie(movie);
        }
    });
    }

}
