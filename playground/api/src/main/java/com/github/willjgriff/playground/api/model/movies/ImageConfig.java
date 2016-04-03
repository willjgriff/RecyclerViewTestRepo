package com.github.willjgriff.playground.api.model.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Will on 03/04/2016.
 */
public class ImageConfig {

    @SerializedName("base_url")
    private String baseUrl;

    @SerializedName("backdrop_sizes")
    private List<String> backdropSizes;

    @SerializedName("logo_size")
    private List<String> logoSizes;

    @SerializedName("poster_sizes")
    private List<String> posterSizes;

    @SerializedName("profile_sizes")
    private List<String> profileSizes;

    @SerializedName("still_sizes")
    private List<String> stillSizes;

    public String getBaseUrl() {
        return baseUrl;
    }

    public List<String> getBackdropSizes() {
        return backdropSizes;
    }

    public List<String> getLogoSizes() {
        return logoSizes;
    }

    public List<String> getPosterSizes() {
        return posterSizes;
    }

    public List<String> getProfileSizes() {
        return profileSizes;
    }

    public List<String> getStillSizes() {
        return stillSizes;
    }
}
