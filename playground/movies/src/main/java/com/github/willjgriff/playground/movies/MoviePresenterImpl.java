package com.github.willjgriff.playground.movies;

import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.movies.Views.MovieView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;

/**
 * Created by Will on 09/04/2016.
 */
public class MoviePresenterImpl extends BasePresenter<Movie, MovieView> implements MoviePresenter {

    @Override
    protected void updateView() {
        view().setImage(model.getBackdropImage());
        view().setTitle(model.getTitle());
        view().setDescription(model.getOverview());
    }
}
