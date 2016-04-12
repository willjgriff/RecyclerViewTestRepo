package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.api.model.movies.MovieListItem;
import com.github.willjgriff.playground.movies.Views.MovieListItemView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;

/**
 * Created by Will on 09/04/2016.
 */
public class MovieListItemPresenterImpl extends BasePresenter<MovieListItem, MovieListItemView> implements MovieListItemPresenter {

    @Override
    protected void updateView() {
        view().setImage(mModel.getBackdropImage());
        view().setTitle(mModel.getTitle());
        view().setDescription(mModel.getOverview());
    }
}
