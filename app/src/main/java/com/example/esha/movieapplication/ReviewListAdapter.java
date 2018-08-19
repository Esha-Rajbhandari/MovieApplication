package com.example.esha.movieapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.esha.movieapplication.Activities.FullMovieActivity;
import com.example.esha.movieapplication.rest.ReviewResult;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewHolder> {
    private static final String TAG = "ReviewListAdapter";
    private Context context;
    private ArrayList<ReviewResult> reviewList;

    public ReviewListAdapter(FullMovieActivity fullMovie, ArrayList<ReviewResult> reviewList) {
        this.context = fullMovie;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View reviewView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list, parent, false);
        ReviewHolder rhHolder = new ReviewHolder(reviewView);
        return rhHolder;
    }

    /***
     * binds the data to display the information in the UI.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: " + reviewList.get(position).getId());
        holder.tvAuthor.setText(reviewList.get(position).getAuthor());
        if (reviewList.get(position).getContent() == null)
            holder.tvContent.setText("No reviews available");
        else
            holder.tvContent.setText(reviewList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        private TextView tvAuthor;
        private TextView tvContent;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.author);
            tvContent = itemView.findViewById(R.id.content);
        }
    }
}
