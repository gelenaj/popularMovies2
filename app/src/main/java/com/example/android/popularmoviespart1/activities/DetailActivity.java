package com.example.android.popularmoviespart1.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.adapters.ReviewAdapter;
import com.example.android.popularmoviespart1.adapters.TrailerAdapter;
import com.example.android.popularmoviespart1.api.MoviesRepo;
import com.example.android.popularmoviespart1.api.OnGetMovieCallback;
import com.example.android.popularmoviespart1.api.OnGetReviewsCallback;
import com.example.android.popularmoviespart1.api.OnGetTrailersCallback;
import com.example.android.popularmoviespart1.database.data.Movie;
import com.example.android.popularmoviespart1.database.data.Review;
import com.example.android.popularmoviespart1.database.data.Trailer;
import com.example.android.popularmoviespart1.viewmodel.DetailViewModel;

import java.util.List;


public class DetailActivity extends AppCompatActivity {
    public static String MOVIE_ID = "movieId";
    private MoviesRepo moviesRepo;
    private RecyclerView recyclerView;
    private DetailViewModel mDetailViewModel;
    private TrailerAdapter mTrailerAdapter;
    private ReviewAdapter mReviewAdapter;
    private int movieId;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private ImageView imageView;
    private TextView titleTv;
    private TextView yearTv;
    private TextView ratingTv;
    private TextView synopsisTv;
    private RecyclerView reviewsRecycler;
    private TextView noReviews;
    private FloatingActionButton favBtn;
    private CoordinatorLayout detailsLayout;
    private boolean isFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.posterIv);
        titleTv = findViewById(R.id.titleTv);
        yearTv = findViewById(R.id.yearTv);
        ratingTv = findViewById(R.id.ratingTv);
        synopsisTv = findViewById(R.id.synopsisTv);
        noReviews = findViewById(R.id.empty_reviews);
        favBtn = findViewById(R.id.fabBtnFav);
        detailsLayout = findViewById(R.id.detailsLayout);
        movieId = getIntent().getIntExtra(MOVIE_ID, movieId);
        moviesRepo = MoviesRepo.getInstance();
        recyclerView = findViewById(R.id.trailerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        reviewsRecycler = findViewById(R.id.reviewsRecycler);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecycler.setHasFixedSize(true);

        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);
        getMovie();
    }

    private void getMovie() {
        moviesRepo.getMovie(movieId, new OnGetMovieCallback() {
            @Override
            public void onSuccess(final Movie movie) {

                titleTv.setText(movie.getTitle());
                synopsisTv.setText(movie.getSynopsis());
                yearTv.setText(getString(R.string.new_year, movie.getReleaseDate().substring(0, 4)));
                ratingTv.setText(getString(R.string.rating_out_of, movie.getRating()));

                Glide.with(DetailActivity.this)
                        .load(IMAGE_BASE_URL + movie.getPoster())
                        .into(imageView);

                getTrailers(movie);
                getReviews(movie);

                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (mDetailViewModel.toggleSaveFavorite(movie.getId(),movie.getTitle(), movie.getPoster(), movie.getSynopsis(),
                                movie.getRating(), movie.getReleaseDate())) {
                            favBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_play_orange));
                            Snackbar.make(detailsLayout, "Saved", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(detailsLayout, "Deleted from favorites", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }

            private void getReviews(Movie movie) {
                moviesRepo.getReviews(movie.getId(), new OnGetReviewsCallback() {

                    @Override
                    public void onSuccess(List<Review> reviews) {
                        mReviewAdapter = new ReviewAdapter(reviews, getApplicationContext());
                        reviewsRecycler.setAdapter(mReviewAdapter);
                        noReviews.setVisibility(View.INVISIBLE);
                        if (reviews.isEmpty()) {
                            noReviews.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(DetailActivity.this, "error.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError() {
                finish();
            }
        });
    }

    private void getTrailers(Movie movie) {
        moviesRepo.getTrailers(movie.getId(), new OnGetTrailersCallback() {
            @Override
            public void onSuccess(final List<Trailer> trailers) {
                mTrailerAdapter = new TrailerAdapter(trailers, getApplicationContext());
                recyclerView.setAdapter(mTrailerAdapter);
            }

            @Override
            public void onError() {
                Toast.makeText(DetailActivity.this, "error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
