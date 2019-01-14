package com.example.android.popularmoviespart1.api;

import com.example.android.popularmoviespart1.database.data.Movie;
import com.example.android.popularmoviespart1.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRepo {
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesRepo repo;
    private MovieApi movieApi;
    private MoviesRepo(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    public static MoviesRepo getInstance() {
        if (repo == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            repo = new MoviesRepo(retrofit.create(MovieApi.class));
        }
        return repo;
    }


    public void getAllMovies(String sortBy, final OnGetMoviesCallback callback) {
        Callback<MoviesResponse> call = new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse movieResponse = response.body();
                    if (movieResponse != null && movieResponse.getMovies() != null) {
                        callback.onSuccess(movieResponse.getMovies());
                    } else {
                        callback.onError();
                    }
                } else {
                    callback.onError();
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                callback.onError();
            }

        };
        switch (sortBy){
            case POPULAR:
                movieApi.getPopularMovies(Constants.API_KEY)
                        .enqueue(call);

                break;

            case TOP_RATED:
                movieApi.getTopRatedMovies(Constants.API_KEY)
                        .enqueue(call);

            break;
            default:
                movieApi.getPopularMovies(Constants.API_KEY)
                        .enqueue(call);

                break;
        }

    }

    public void getMovie(int movieId, final OnGetMovieCallback callback){
        movieApi.getMovie(movieId, Constants.API_KEY)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if(response.isSuccessful()){
                            Movie movie = response.body();
                            if(movie != null){
                                callback.onSuccess(movie);
                            }else{
                                callback.onError();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getTrailers(int movieId, final OnGetTrailersCallback callback) {
        movieApi.getVideos(movieId, Constants.API_KEY)
                .enqueue(new Callback<TrailerResponse>() {
                    @Override
                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                        if (response.isSuccessful()) {
                            TrailerResponse trailerResponse = response.body();

                            if (trailerResponse != null && trailerResponse.getResults() != null) {
                                callback.onSuccess(trailerResponse.getResults());
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }

                    }

                    @Override
                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public void getReviews(int movieId, final OnGetReviewsCallback callback){
        movieApi.getReviews(movieId,  Constants.API_KEY)
                .enqueue(new Callback<ReviewResponse>() {
                    @Override
                    public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                        if (response.isSuccessful()) {
                            ReviewResponse reviewResponse = response.body();

                            if(reviewResponse !=null && reviewResponse.getResults() != null){
                                callback.onSuccess(reviewResponse.getResults());
                            }else{
                                callback.onError();
                            }
                        }else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReviewResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}



