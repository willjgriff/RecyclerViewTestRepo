package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.network.TheMovieDb;
import com.github.willjgriff.playground.network.model.movies.MovieFull;
import com.github.willjgriff.playground.movies.Views.MovieDetailsView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieDetailsPresenterImpl extends BasePresenter<MovieFull, MovieDetailsView> implements MovieDetailsPresenter {

    String mMovieId;

    public MovieDetailsPresenterImpl(String movieId) {
        this.mMovieId = movieId;
        TheMovieDb.movieDetailsCall(mMovieId).enqueue(new Callback<MovieFull>() {
            @Override
            public void onResponse(Response<MovieFull> response, Retrofit retrofit) {
                setModel(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    protected void updateView() {
        view().setName(mModel.getTitle());
        view().setPoster(mModel.getPosterImage());
    }
}
