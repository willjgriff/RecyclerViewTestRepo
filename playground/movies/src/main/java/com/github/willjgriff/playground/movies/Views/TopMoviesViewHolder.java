package com.github.willjgriff.playground.movies.Views;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.movies.MoviePresenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpViewHolder;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesViewHolder extends MvpViewHolder<MoviePresenter> implements MovieView {

    private ImageView mMovieBackdrop;

    public TopMoviesViewHolder(View itemView) {
        super(itemView);
        mMovieBackdrop = (ImageView) itemView.findViewById(R.id.view_movie_item_image);
    }

    @Override
    public void setImage(String backdropImage) {

        // TODO: This needs moving to a UTILS.
//        MoviesConfig moviesConfig = readObjectFromPreferences(itemView.getContext(), SharedPreferenceUtils.SHARED_MOVIES_CONFIG, MoviesConfig.class);
//        String backdropSize = null;
//        if (!isNull(moviesConfig)) {
//            List<String> backdropSizes = moviesConfig.getImageConfig().getBackdropSizes();
//            backdropSize = backdropSizes.get(backdropSizes.size() - 1);
//        }

        mMovieBackdrop.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), R.drawable.red_panda));

//        ApiMovieImageUtils.showImage(backdropImage, mMovieBackdrop)
//                .withPlaceholder(R.drawable.movie_banner_placeholder)
////                .withImageSize("w300")
//                .now();
    }
}
