package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.api.RetrofitCalls;
import com.github.willjgriff.playground.api.model.movies.MovieFull;
import com.github.willjgriff.playground.movies.Views.MovieDetailsView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BaseLoadingPresenter;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieDetailsPresenterImpl extends BaseLoadingPresenter<MovieFull, MovieDetailsView> implements MovieDetailsPresenter {

    String mMovieId;

    public MovieDetailsPresenterImpl(String movieId) {
        this.mMovieId = movieId;
    }

    @Override
    protected void loadDataModel() {
        setLoading(true);
        RetrofitCalls.movieDetailsCall(mMovieId).enqueue(new Callback<MovieFull>() {
            @Override
            public void onResponse(Response<MovieFull> response, Retrofit retrofit) {
                setModel(response.body());
                setLoading(false);
            }

            @Override
            public void onFailure(Throwable t) {
                setLoading(false);
            }
        });
    }
}
