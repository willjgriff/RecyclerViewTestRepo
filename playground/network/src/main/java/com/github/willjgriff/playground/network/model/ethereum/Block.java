package com.github.willjgriff.playground.network.model.ethereum;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 18/04/2016.
 */
public class Block {

    @SerializedName("number")
    private long number;

    @SerializedName("hash")
    private String hash;

    public long getNumber() {
        return number;
    }

    public String getHash() {
        return hash;
    }
}
