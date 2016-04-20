package com.github.willjgriff.playground.network.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Will on 11/04/2016.
 */
public class MovieFull extends MovieListItem {

    @SerializedName("budget")
    private long budget;

    @SerializedName("revenue")
    private long revenue;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("runtime")
    private long runtime;


    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public long getRuntime() {
        return runtime;
    }
}
