package com.github.willjgriff.playground.movies.data;

import com.github.willjgriff.playground.network.api.TheMovieDb.TheMovieDbCalls;
import com.github.willjgriff.playground.network.model.movies.TopMovies;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Will on 18/07/2016.
 */
public class TopMoviesData {

	Call<TopMovies> mTopMoviesCall;

	public void getTopMovies(TopMoviesDataListener topMoviesDataListener) {
		mTopMoviesCall = TheMovieDbCalls.topMoviesCall();
		mTopMoviesCall.enqueue(new Callback<TopMovies>() {
			@Override
			public void onResponse(Response<TopMovies> response, Retrofit retrofit) {
				topMoviesDataListener.success(response.body());
			}

			@Override
			public void onFailure(Throwable t) {
				topMoviesDataListener.failed(t);
			}
		});
	}

	public void cancel() {
		if (mTopMoviesCall != null) {
			mTopMoviesCall.cancel();
			mTopMoviesCall = null;
		}
	}
}
