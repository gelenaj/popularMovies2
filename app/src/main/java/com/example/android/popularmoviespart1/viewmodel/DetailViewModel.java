package com.example.android.popularmoviespart1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmoviespart1.database.AppDbRepo;
import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailViewModel extends AndroidViewModel {
    private AppDbRepo appDbRepo;
    private MutableLiveData<Movie> mLiveMovie =
            new MutableLiveData<>();
    private Executor executor = Executors.newSingleThreadExecutor();



    public DetailViewModel(@NonNull Application application) {
        super(application);
        appDbRepo = AppDbRepo.getInstance(getApplication());
    }

    public void loadData(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Movie note = appDbRepo.getMovieById(noteId);
                mLiveMovie.postValue(note);
            }
        });
    }


    public boolean saveFavorite(String title, String poster, String synopsis, String rating, String date) {
        Movie movie = appDbRepo.getMovieByTitle(title);

            if(movie == null) {
                appDbRepo.insertNewFavMovie(title, poster, synopsis, rating, date);

                return true;
            }else{


                return false;
            }
    }
}
