package com.github.willjgriff.playground.movies.data;

import com.github.willjgriff.playground.network.model.movies.TopMovies;

/**
 * Created by Will on 18/07/2016.
 */

public interface TopMoviesDataListener {
	void success(TopMovies topMoviesResponse);

	void failed(Throwable throwable);
}
