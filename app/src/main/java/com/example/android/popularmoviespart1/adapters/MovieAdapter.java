package com.example.android.popularmoviespart1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.api.OnMoviesClickCallback;
import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String LOG_TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movies;
    private OnMoviesClickCallback callback;

    public MovieAdapter(List<Movie> movies, OnMoviesClickCallback callback) {
        this.movies = movies;
        this.callback = callback;
    }


    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = getItem(position);
        viewHolder.bind(movie);
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView poster;
        Movie movie;


        private ViewHolder(final View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View v) {
             callback.onClick(movie);
              }
            });
        }

        private void bind(final Movie movie) {
            this.movie = movie;
            String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
        }
    }

}
