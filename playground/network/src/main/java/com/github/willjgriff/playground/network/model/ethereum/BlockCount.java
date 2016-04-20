package com.github.willjgriff.playground.network.model.ethereum;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Will on 18/04/2016.
 */
public class BlockCount {

    @SerializedName("count")
    private long count;

    public long getCount() {
        return count;
    }
}
