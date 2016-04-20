package com.github.willjgriff.playground.network;

import com.github.willjgriff.playground.network.endpoints.ApiEtherchain;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;
import com.github.willjgriff.playground.network.model.ethereum.responses.BlockCountResponse;
import com.github.willjgriff.playground.network.model.ethereum.responses.EtherchainResponse;
import com.github.willjgriff.playground.network.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.ApiUris.Etherchain.URI_ETHERCHAIN;

/**
 * Created by Will on 28/03/2016.
 */
public class Etherchain {

    private static ApiEtherchain getApiEtherchain() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .create();

        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_ETHERCHAIN, null, GsonConverterFactory.create(gson));
        return retrofit.create(ApiEtherchain.class);
    }

    public static Call<BlockCount> blockCountCall() {
        return getApiEtherchain().totalBlockCount();
    }

    public static Call<EtherchainResponse> blockList() {
        return getApiEtherchain().blocks(0, 1);
    }
}
