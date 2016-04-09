package com.github.willjgriff.playground.movies.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.ApiMovieImageUtils;
import com.github.willjgriff.playground.movies.MoviePresenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpViewHolder;

import static com.github.willjgriff.playground.movies.Utils.MovieImageSize.ImageSize.MEDIUM;
import static com.github.willjgriff.playground.movies.Utils.MovieImageSize.ImageType.BACKDROP;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesViewHolder extends MvpViewHolder<MoviePresenter> implements MovieView {

    private ImageView mMovieBackdrop;
    private TextView mTitle;
    private TextView mDescription;

    public TopMoviesViewHolder(View itemView) {
        super(itemView);
        mMovieBackdrop = (ImageView) itemView.findViewById(R.id.view_movie_item_image);
        mTitle = (TextView) itemView.findViewById(R.id.view_movie_item_title);
        mDescription = (TextView) itemView.findViewById(R.id.view_movie_item_description);
    }

    @Override
    public void setImage(String backdropImage) {
        ApiMovieImageUtils.showImage(backdropImage, mMovieBackdrop)
                .withPlaceholder(R.drawable.movie_banner_placeholder)
                .withTypeSize(BACKDROP, MEDIUM)
                .now();
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setDescription(String overview) {
        mDescription.setText(overview);
    }
}
