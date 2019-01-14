package com.example.android.popularmoviespart1.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmoviespart1.database.AppDbRepo;
import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public LiveData<List<Movie>> mMovies;

    public MainViewModel(@NonNull Application application) {
        super(application);

        AppDbRepo mRepository = AppDbRepo.getInstance(application.getApplicationContext());
        mMovies = mRepository.mMovies;

    }


}
