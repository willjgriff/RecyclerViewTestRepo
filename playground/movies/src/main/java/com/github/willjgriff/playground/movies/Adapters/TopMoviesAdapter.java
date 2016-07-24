package com.github.willjgriff.playground.movies.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.network.utils.MovieApiImageUtils;

import java.util.ArrayList;
import java.util.List;

import static com.github.willjgriff.playground.network.utils.MovieImageSize.ImageSize.SMALL;
import static com.github.willjgriff.playground.network.utils.MovieImageSize.ImageType.BACKDROP;

/**
 * Created by Will on 30/03/2016.
 */
public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder> {

    List<MovieListItem> mMovies = new ArrayList<>();

    @Override
    public TopMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_item, parent, false);
        return new TopMoviesViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(TopMoviesViewHolder holder, int position) {
        holder.bindView(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setMovies(List<MovieListItem> movies) {
        mMovies = new ArrayList<>(movies);
        notifyDataSetChanged();
    }

    class TopMoviesViewHolder extends RecyclerView.ViewHolder {

        public CardView mLayoutView;
        public ImageView mMovieImage;

        public TopMoviesViewHolder(View itemView) {
            super(itemView);
            mLayoutView = (CardView) itemView.findViewById(R.id.view_movie_item_container);
            mMovieImage = (ImageView) itemView.findViewById(R.id.view_movie_item_image);
        }

        public void bindView(MovieListItem movieListItem) {
            MovieApiImageUtils.showImage(movieListItem.getBackdropImage(), mMovieImage)
                    .withPlaceholder(R.drawable.movie_banner_placeholder)
                    .withTypeSize(BACKDROP, SMALL)
                    .now();
        }
    }
}
