package com.github.willjgriff.playground.api.model.movies;

import com.github.willjgriff.playground.api.model.Entity;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Will on 26/03/2016.
 */
public class MovieListItem implements Entity {

    public static final String RELEASE_DATE_FORMAT = "yyyy-MM-dd";

    @SerializedName("id")
    private String id;

    @SerializedName("original_title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private Date releaseDate;

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

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public String getBackdropImage() {
        return backdropPath;
    }
}
