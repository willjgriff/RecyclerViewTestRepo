package com.github.willjgriff.playground.movies.Views;

import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpListLoadingView;

/**
 * Created by Will on 09/04/2016.
 */
public interface TopMoviesView extends MvpListLoadingView<MovieListItem> {

    void openMovieDetailScreen(String movieId);
}
