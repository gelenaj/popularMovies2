package com.example.android.popularmoviespart1.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.popularmoviespart1.R;
import com.example.android.popularmoviespart1.database.data.Trailer;
import com.example.android.popularmoviespart1.utils.Constants;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private final List<Trailer> mTrailers;

    public TrailerAdapter(List<Trailer> mTrailers, Context mContext) {
        this.mTrailers = mTrailers;
        Context mContext1 = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trailer_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Trailer trailer = mTrailers.get(i);
        viewHolder.trailerTitle.setText(trailer.getmTrailerTitle());

        viewHolder.trailerTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Constants.YOUTUBE_BASE_URL + mTrailers.get(viewHolder.getAdapterPosition()).getmVideoKey()));
                    v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mTrailers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView trailerTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerTitle = itemView.findViewById(R.id.trailerTitle);
        }
    }
}
