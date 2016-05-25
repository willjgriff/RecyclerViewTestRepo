package com.github.willjgriff.playground.movies.Adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.movies.Presenters.MovieListItemPresenter;
import com.github.willjgriff.playground.movies.Presenters.MovieListItemPresenterImpl;
import com.github.willjgriff.playground.movies.Views.MovieListItemViewHolder;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.Adapter.MvpRecyclerListAdapter;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesMvpAdapter extends MvpRecyclerListAdapter<MovieListItem, MovieListItemPresenter, MovieListItemViewHolder> {

    @NonNull
    @Override
    protected MovieListItemPresenter createPresenter(@NonNull MovieListItem model) {
        MovieListItemPresenter movieListItemPresenter = new MovieListItemPresenterImpl();
        movieListItemPresenter.setModel(model);
        return movieListItemPresenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull MovieListItem model) {
        return model.getId();
    }

    @Override
    protected MovieListItemViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return new MovieListItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_item, parent, false));
    }
}
