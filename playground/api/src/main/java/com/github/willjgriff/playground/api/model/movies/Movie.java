package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 26/03/2016.
 */
public class Movie {

    // For Gson conversion
//    public String DATE_FORMAT = "yyyy-MM-dd";

    @SerializedName("id")
    private String id;

    @SerializedName("original_title")
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterImage;

    @SerializedName("backdrop_path")
    private String backdropPath;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
