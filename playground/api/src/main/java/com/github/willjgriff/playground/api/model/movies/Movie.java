package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 26/03/2016.
 */
public class Movie {

    public String DATE_FORMAT = "yyyy-MM-dd";

    @SerializedName("id")
    String id;

    @SerializedName("original_title")
    String title;

    @SerializedName("release_date")
    String releaseDate;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return title;
    }


}
