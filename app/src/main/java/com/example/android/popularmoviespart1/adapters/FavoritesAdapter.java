package com.example.android.popularmoviespart1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.activities.DetailActivity;
import com.example.android.popularmoviespart1.activities.MainActivity;
import com.example.android.popularmoviespart1.api.OnMoviesClickCallback;
import com.example.android.popularmoviespart1.database.data.Movie;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private OnMoviesClickCallback callback;

    public FavoritesAdapter(List<Movie> mMovies, OnMoviesClickCallback callback) {
        this.mMovies = mMovies;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //final Movie movie = mMovies.get(position);
        holder.bind(mMovies.get(position));
        //holder.bind(movie);


    }


    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        Movie movie;



        private ViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    callback.onClick(movie);

                }
            });

        }
        private void bind(Movie movie) {
            this.movie = movie;

            String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";
            Glide.with(itemView)
                    .load(IMAGE_BASE_URL + movie.getPoster())
                    .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                    .into(poster);
        }
    }
}
