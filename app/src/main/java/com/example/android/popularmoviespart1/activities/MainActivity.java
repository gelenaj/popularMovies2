package com.example.android.popularmoviespart1.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.adapters.FavoritesAdapter;
import com.example.android.popularmoviespart1.adapters.MovieAdapter;
import com.example.android.popularmoviespart1.api.MoviesRepo;
import com.example.android.popularmoviespart1.api.OnGetMoviesCallback;
import com.example.android.popularmoviespart1.api.OnMoviesClickCallback;
import com.example.android.popularmoviespart1.database.data.Movie;
import com.example.android.popularmoviespart1.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MoviesRepo moviesRepo;
    private ProgressBar mProgressBar;
    private TextView mEmptyStateTextView;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private String orderBy = "popular";
    private FavoritesAdapter mFavoritesAdapter;
    private List<Movie> movieData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        moviesRepo = MoviesRepo.getInstance();
        mProgressBar = findViewById(R.id.progressBar);
        mEmptyStateTextView = findViewById(R.id.empty);
        int numOfColumns = 2;
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numOfColumns));
        recyclerView.setHasFixedSize(true);

        getMovies();
    }

    private void initViewModel() {

        final Observer<List<Movie>> movieObserver = new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                movieData.clear();
                movieData.addAll(movieList);
                if (mFavoritesAdapter == null) {
                    mFavoritesAdapter = new FavoritesAdapter(movieData, MainActivity.this);
                    recyclerView.setAdapter(mFavoritesAdapter);
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mFavoritesAdapter.notifyDataSetChanged();

                }

            }

        };



        MainViewModel mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mViewModel.mMovies.observe(this, movieObserver);
    }

    private void getMovies(){
        moviesRepo.getAllMovies(orderBy, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                    mAdapter = new MovieAdapter(movies, callback);
                    recyclerView.setAdapter(mAdapter);
                    mProgressBar.setVisibility(View.GONE);
            }


            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
                mEmptyStateTextView.setVisibility(View.VISIBLE);
                mEmptyStateTextView.setText(getString(R.string.no_movies));

            }
        });
    }


    OnMoviesClickCallback callback = new OnMoviesClickCallback() {
    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.MOVIE_ID, movie.getId());
        startActivity(intent);
    }


};

@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_popular:
                setTitle("Most Popular");
                orderBy = MoviesRepo.POPULAR;
                getMovies();
                return true;
            case R.id.navigation_top_rated:
                setTitle("Top Rated");
                orderBy = MoviesRepo.TOP_RATED;
                getMovies();
                return true;
            case R.id.navigation_favorites:
                mAdapter.notifyDataSetChanged();
                setTitle("My Favorite Movies");
               initViewModel();

                return true;
        }
        return false;
    }


}

