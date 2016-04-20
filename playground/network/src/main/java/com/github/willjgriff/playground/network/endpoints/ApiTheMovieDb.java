package com.github.willjgriff.playground.network.endpoints;

import com.github.willjgriff.playground.network.model.movies.MovieFull;
import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.network.model.movies.TopMovies;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Will on 26/03/2016.
 */
public interface ApiTheMovieDb {

    @GET("3/movie/popular")
    Call<TopMovies> listTopMovies();

    @GET("3/configuration")
    Call<MoviesConfig> imageConfig();

    @GET("3/movie/{id}")
    Call<MovieFull> movie(@Path("id") String id);

}
