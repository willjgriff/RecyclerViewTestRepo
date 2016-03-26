package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 26/03/2016.
 */
public class TopMovies {

    List<Movie> topMovies = new ArrayList<>();

    public List<Movie> getTopMovies() {
        return topMovies;
    }

    public static TopMovies parseResponse(String response) {
        Gson gson = new GsonBuilder().create();
        TopMovies topMoviesList = gson.fromJson(response, TopMovies.class);
        return topMoviesList;
    }
}
