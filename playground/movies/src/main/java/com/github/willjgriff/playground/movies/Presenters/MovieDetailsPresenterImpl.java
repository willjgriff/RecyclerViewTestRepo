package com.github.willjgriff.playground.movies.Presenters;

import com.github.willjgriff.playground.movies.Views.MovieDetailsView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;
import com.github.willjgriff.playground.network.model.movies.MovieFull;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieDetailsPresenterImpl extends BasePresenter<MovieFull, MovieDetailsView> implements MovieDetailsPresenter {

    private String mMovieId;
    private Call<MovieFull> mMovieFullCall;

    public MovieDetailsPresenterImpl(String movieId, Call<MovieFull> apiRequest) {
        mMovieId = movieId;
        mMovieFullCall = apiRequest;
        mMovieFullCall.enqueue(new Callback<MovieFull>() {
            @Override
            public void onResponse(Response<MovieFull> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    setModel(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }

    @Override
    protected void updateView() {
        view().setMovieName(mModel.getTitle());
        view().setPoster(mModel.getPosterImage());
    }
}
