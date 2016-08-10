package com.github.willjgriff.playground.network.model.stackoverflow;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Will on 26/03/2016.
 */
public class StackOverflowQuestion extends RealmObject {

    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    // Needed to use default ArrayAdapter to return title.
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
