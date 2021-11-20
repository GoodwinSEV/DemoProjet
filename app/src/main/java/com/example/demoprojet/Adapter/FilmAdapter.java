package com.example.demoprojet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demoprojet.GetterAndSetter.Movies;
import com.example.demoprojet.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FilmAdapter extends  RecyclerView.Adapter<FilmAdapter.ViewHolder>{

    private final static String PHOTO_URL = "http://cinema.areas.su/up/images/";

    private List<Movies> mMovies1;
    private Context mContext;

    public FilmAdapter(List<Movies> movies) {
        this.mMovies1 = movies;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movies movie = mMovies1.get(position);
        Picasso.with(mContext)
                .load(PHOTO_URL + movie.getPoster())
                .resize(500,700)
                .into(holder.posterImageView);

    }

    @Override
    public int getItemCount() {
        if (mMovies1 == null) {
            return 0;
        }
        return mMovies1.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        ImageView posterImageView;

        ViewHolder(View itemView) {
            super(itemView);
            posterImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}