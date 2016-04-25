package com.github.willjgriff.playground.network.endpoints;

import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Will on 18/04/2016.
 */
public interface ApiEtherchain {

    @GET("api/blocks/count")
    Observable<BlockCount> totalBlockCount();

    @GET("api/blocks/{offset}/{count}")
    Observable<List<Block>> blocks(@Path("offset") int offset, @Path("count") int count);
}
