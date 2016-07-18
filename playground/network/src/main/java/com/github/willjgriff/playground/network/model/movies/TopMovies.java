package com.github.willjgriff.playground.network.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 26/03/2016.
 */
public class TopMovies {

    @SerializedName("results")
    private List<MovieListItem> topMovies = new ArrayList<>();

    public List<MovieListItem> getTopMovies() {
        return topMovies;
    }

    // Used only for creating mocks for use in automation testing.
    public void setTopMovies(List<MovieListItem> topMovies) {
        this.topMovies = topMovies;
    }

//    public static TopMovies parseResponse(String response) {
//        Gson gson = new GsonBuilder().create();
//        TopMovies topMoviesList = gson.fromJson(response, TopMovies.class);
//        return topMoviesList;
//    }
}
