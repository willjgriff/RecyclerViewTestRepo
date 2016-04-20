package com.github.willjgriff.playground.network.model.ethereum.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Will on 18/04/2016.
 */
public class EtherchainResponse<TYPE> {

    @SerializedName("status")
    private int status;

    @SerializedName("data")
    private List<TYPE> data;

    public int getStatus() {
        return status;
    }

    public List<TYPE> getData() {
        return data;
    }
}
