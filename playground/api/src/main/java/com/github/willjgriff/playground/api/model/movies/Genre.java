package com.github.willjgriff.playground.api.model.movies;

import com.github.willjgriff.playground.api.model.Entity;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 11/04/2016.
 */
public class Genre implements Entity {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
