package com.github.willjgriff.playground.movies.Presenters;

import android.util.Log;

import com.github.willjgriff.playground.movies.Views.MovieDetailsView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;
import com.github.willjgriff.playground.network.model.movies.MovieFull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieDetailsPresenterImpl extends BasePresenter<MovieFull, MovieDetailsView> implements MovieDetailsPresenter {

    private Call<MovieFull> mMovieFullCall;

    public MovieDetailsPresenterImpl(Call<MovieFull> apiRequest) {
        mMovieFullCall = apiRequest;
        mMovieFullCall.enqueue(new Callback<MovieFull>() {
            @Override
            public void onResponse(Call<MovieFull> call, Response<MovieFull> response) {
                if (response.isSuccessful()) {
                    setModel(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieFull> call, Throwable t) {
                Log.d("TAG", "Movie Details request failed");
            }
        });
    }

    @Override
    protected void updateView() {
        view().setMovieName(mModel.getTitle());
        view().setPoster(mModel.getPosterImage());
    }

    @Override
    public void unbindView() {
        super.unbindView();
        if (mMovieFullCall != null) {
            mMovieFullCall.cancel();
            mMovieFullCall = null;
        }
    }
}
