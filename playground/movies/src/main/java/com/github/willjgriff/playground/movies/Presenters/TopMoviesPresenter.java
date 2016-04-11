package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;

import java.util.List;

/**
 * Created by Will on 09/04/2016.
 */
public interface TopMoviesPresenter extends Presenter<List<Movie>, TopMoviesView> {
    void fetchTopMovies();
}
