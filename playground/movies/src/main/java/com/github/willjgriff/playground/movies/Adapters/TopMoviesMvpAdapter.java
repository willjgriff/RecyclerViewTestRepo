package com.github.willjgriff.playground.movies.Adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.movies.Presenters.MoviePresenter;
import com.github.willjgriff.playground.movies.Presenters.MoviePresenterImpl;
import com.github.willjgriff.playground.movies.Views.TopMoviesViewHolder;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Adapter.MvpRecyclerListAdapter;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesMvpAdapter extends MvpRecyclerListAdapter<Movie, MoviePresenter, TopMoviesViewHolder> {

    @NonNull
    @Override
    protected MoviePresenter createPresenter(@NonNull Movie model) {
        MoviePresenter moviePresenter = new MoviePresenterImpl();
        moviePresenter.setModel(model);
        return moviePresenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Movie model) {
        return model.getId();
    }

    @Override
    public TopMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopMoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_item, parent, false));
    }
}
