package com.github.willjgriff.playground.api.endpoints;

import com.github.willjgriff.playground.api.model.movies.MoviesConfig;
import com.github.willjgriff.playground.api.model.movies.TopMovies;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Will on 26/03/2016.
 */
public interface ApiTheMovieDb {

    @GET("3/movie/popular")
    Call<TopMovies> listTopMovies();

    @GET("3/configuration")
    Call<MoviesConfig> imageConfig();

}
