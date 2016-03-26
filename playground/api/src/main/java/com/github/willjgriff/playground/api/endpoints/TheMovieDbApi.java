package com.github.willjgriff.playground.api.endpoints;

import com.github.willjgriff.playground.api.model.movies.TopMovies;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Will on 26/03/2016.
 */
public interface TheMovieDbApi {

    @GET("/lists/movies/box_office.json")
    public Call<TopMovies> listRepos();
}
