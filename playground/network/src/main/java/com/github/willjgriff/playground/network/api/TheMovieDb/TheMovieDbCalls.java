package com.github.willjgriff.playground.network.api.TheMovieDb;

import com.github.willjgriff.playground.network.model.movies.MovieFull;
import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.network.model.movies.TopMovies;

import retrofit2.Call;


/**
 * Created by Will on 11/05/2016.
 */
public class TheMovieDbCalls {

    public static Call<TopMovies> topMoviesCall() {
        return TheMovieDbApi.getTheMovieDbApi().listTopMovies();
    }

    public static Call<MoviesConfig> moviesConfigCall() {
        return TheMovieDbApi.getTheMovieDbApi().imageConfig();
    }

    public static Call<MovieFull> movieDetailsCall(String movieId) {
        return TheMovieDbApi.getTheMovieDbApi().movie(movieId);
    }
}
