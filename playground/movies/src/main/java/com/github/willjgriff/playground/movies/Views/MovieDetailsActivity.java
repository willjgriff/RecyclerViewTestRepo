package com.github.willjgriff.playground.movies.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.MovieFull;
import com.github.willjgriff.playground.movies.Presenters.MovieDetailsPresenter;
import com.github.willjgriff.playground.movies.Presenters.MovieDetailsPresenterImpl;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpActivity;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieDetailsActivity extends MvpActivity<MovieDetailsPresenter> implements MovieDetailsView {

    public static final String EXTRA_MOVIE_ID = "com.github.willjgriff.playground.movies.Views.MovieDetailsActivity;EXTRA_MOVIE_ID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_top_movies);
    }

    @Override
    protected MovieDetailsPresenter setPresenter() {
        String movidId = getIntent().getExtras().getString(EXTRA_MOVIE_ID);
        return new MovieDetailsPresenterImpl(movidId);
    }

    @Override
    public void showData(MovieFull movieFull) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showLoading() {

    }
}
