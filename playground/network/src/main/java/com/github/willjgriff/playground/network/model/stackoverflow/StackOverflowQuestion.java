package com.github.willjgriff.playground.network.model.stackoverflow;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 26/03/2016.
 */
public class StackOverflowQuestion {

    @SerializedName("title")
    String title;

    @SerializedName("link")
    String link;

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

}
