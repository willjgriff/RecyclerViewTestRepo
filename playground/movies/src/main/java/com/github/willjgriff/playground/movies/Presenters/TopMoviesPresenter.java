package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.api.model.movies.MovieListItem;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.LoadingPresenter;

import java.util.List;

/**
 * Created by Will on 09/04/2016.
 */
public interface TopMoviesPresenter extends LoadingPresenter<List<MovieListItem>, TopMoviesView> {

    void onMovieItemClicked(String movieId);
}
