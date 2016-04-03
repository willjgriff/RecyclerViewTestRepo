package com.github.willjgriff.playground.movies;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.ApiMovieImageUtils;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.api.model.movies.MoviesConfig;
import com.github.willjgriff.playground.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import static com.github.willjgriff.playground.utils.Defaults.isNull;
import static com.github.willjgriff.playground.utils.SharedPreferenceUtils.readObjectFromPreferences;

/**
 * Created by Will on 30/03/2016.
 */
public class TopMoviesAdapter extends RecyclerView.Adapter<TopMoviesAdapter.TopMoviesViewHolder> {

    List<Movie> mMovies = new ArrayList<>();
    Context mContext;

    public TopMoviesAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public TopMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(mContext).inflate(R.layout.view_movie_item, parent, false);
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

    public void setMovies(List<Movie> movies) {
        mMovies = new ArrayList<>(movies);
        notifyDataSetChanged();
    }

    class TopMoviesViewHolder extends RecyclerView.ViewHolder {

        public CardView mLayoutView;
        public ImageView mMovieImage;
        public TextView mMovieName;
        public TextView mMovieDescription;

        public TopMoviesViewHolder(View itemView) {
            super(itemView);
            mLayoutView = (CardView) itemView.findViewById(R.id.view_movie_item_container);
            mMovieImage = (ImageView) itemView.findViewById(R.id.view_movie_item_image);
//            mMovieName = (TextView) itemView.findViewById(R.id.view_movie_item_name);
//            mMovieDescription = (TextView) itemView.findViewById(R.id.view_movie_item_description);
        }

        public void bindView(Movie movie) {
            MoviesConfig moviesConfig = readObjectFromPreferences(itemView.getContext(), SharedPreferenceUtils.SHARED_MOVIES_CONFIG, MoviesConfig.class);
            String smallPosterSize = null;
            if (!isNull(moviesConfig)) {
                List<String> backdropSizes = moviesConfig.getImageConfig().getBackdropSizes();
                smallPosterSize = backdropSizes.get(backdropSizes.size() - 2);
            }
            ApiMovieImageUtils.showImage(movie.getBackdropPath(), mMovieImage)
                    .withPlaceholder(R.drawable.movie_banner_placeholder)
                    .withImageSize(smallPosterSize)
                    .now();
        }
    }
}
