package com.github.willjgriff.playground.movies.data;

import com.github.willjgriff.playground.network.api.TheMovieDb.TheMovieDbCalls;
import com.github.willjgriff.playground.network.model.movies.TopMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Will on 18/07/2016.
 */
public class TopMoviesData {

	Call<TopMovies> mTopMoviesCall;

	public void getTopMovies(TopMoviesDataListener topMoviesDataListener) {
		mTopMoviesCall = TheMovieDbCalls.topMoviesCall();
		mTopMoviesCall.enqueue(new Callback<TopMovies>() {
			@Override
			public void onResponse(Call<TopMovies> call, Response<TopMovies> response) {
				topMoviesDataListener.success(response.body());
			}

			@Override
			public void onFailure(Call<TopMovies> call, Throwable t) {
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
