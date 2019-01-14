package com.example.android.popularmoviespart1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.database.data.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private final List<Review> reviewList;

    public ReviewAdapter(List<Review> reviewList, Context mContext) {
        this.reviewList = reviewList;
        Context mContext1 = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Review review = reviewList.get(i);
            viewHolder.author.setText(review.getAuthor());
            viewHolder.review.setText(review.getContent());

    }
    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView review;
        TextView author;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            author= itemView.findViewById(R.id.author_tv);
             review= itemView.findViewById(R.id.review_tv);

        }
    }
}
