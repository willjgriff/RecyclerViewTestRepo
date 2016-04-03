package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 26/03/2016.
 */
public class TopMovies {

    @SerializedName("results")
    private List<Movie> topMovies = new ArrayList<>();

    public List<Movie> getTopMovies() {
        return topMovies;
    }

//    public static TopMovies parseResponse(String response) {
//        Gson gson = new GsonBuilder().create();
//        TopMovies topMoviesList = gson.fromJson(response, TopMovies.class);
//        return topMoviesList;
//    }
}
