package com.github.willjgriff.playground.movies.Presenters;

import android.util.Log;

import com.github.willjgriff.playground.api.RetrofitCalls;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.api.model.movies.TopMovies;
import com.github.willjgriff.playground.movies.Views.TopMoviesView;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.ListLoadingPresenter;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 09/04/2016.
 */
public class TopMoviesPresenterImpl extends ListLoadingPresenter<Movie, TopMoviesView> implements TopMoviesPresenter {

    // TODO: Find out if we need to cancel ApiCalls when the Activity is destroyed.
    // I don't think we do because the Presenter detaches from the View so we can't
    // get null pointer exceptions. The only issue may be a hanging call that doesn't
    // cancel ever. I expect that Retrofit fails them, should check.
    public TopMoviesPresenterImpl() {
        fetchTopMovies();
    }

    @Override
    public void fetchTopMovies() {
        setLoading(true);
        RetrofitCalls.topMoviesCall().enqueue(new Callback<TopMovies>() {
            @Override
            public void onResponse(Response<TopMovies> response, Retrofit retrofit) {
                setModel(response.body().getTopMovies());
                setLoading(false);
            }

            @Override
            public void onFailure(Throwable t) {
                setLoading(false);
                // TODO: Find an Android-less way of communicating errors.
                Log.e("Tag", "Failed to connect to The Move Db");
            }
        });
    }
}
