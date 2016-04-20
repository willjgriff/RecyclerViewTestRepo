package com.github.willjgriff.playground.network;

import com.github.willjgriff.playground.network.endpoints.ApiEtherchain;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;
import com.github.willjgriff.playground.network.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.ApiUris.Etherchain.URI_ETHERCHAIN;

/**
 * Created by Will on 28/03/2016.
 */
public class Etherchain {

    private static ApiEtherchain getApiEtherchain(TypeAdapterFactory typeAdapterFactory) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();

        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_ETHERCHAIN, null, GsonConverterFactory.create(gson));
        return retrofit.create(ApiEtherchain.class);
    }

    public static Call<BlockCount> blockCountCall() {
        Type typeListBlock = new TypeToken<BlockCount>() {}.getType();
        return getApiEtherchain(new EtherchainTypeAdapterFactory(true)).totalBlockCount();
    }

    public static Call<List<Block>> blockList(int offset, int count) {
        Type typeListBlock = new TypeToken<List<Block>>() {}.getType();
        return getApiEtherchain(new EtherchainTypeAdapterFactory(false, typeListBlock)).blocks(offset, count);
    }
}
