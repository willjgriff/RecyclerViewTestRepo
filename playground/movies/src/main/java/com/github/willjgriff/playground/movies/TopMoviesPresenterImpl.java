package com.github.willjgriff.playground.movies;

import android.util.Log;

import com.github.willjgriff.playground.api.RetrofitCalls;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.api.model.movies.TopMovies;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.ListLoadingPresenter;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesPresenterImpl extends ListLoadingPresenter<Movie, TopMoviesView> implements TopMoviesPresenter {

    private Call<TopMovies> topMoviesCall;

    public TopMoviesPresenterImpl() {
        topMoviesCall = RetrofitCalls.topMoviesCall();
        topMoviesCall.enqueue(new Callback<TopMovies>() {
            @Override
            public void onResponse(Response<TopMovies> response, Retrofit retrofit) {
                setModel(response.body().getTopMovies());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Tag", "Failed to connect to The Move Db");
            }
        });
    }
}
