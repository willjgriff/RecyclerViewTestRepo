package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 03/04/2016.
 */
public class MoviesConfig {

    @SerializedName("images")
    private ImageConfig imageConfig;

    public ImageConfig getImageConfig() {
        return imageConfig;
    }
}
