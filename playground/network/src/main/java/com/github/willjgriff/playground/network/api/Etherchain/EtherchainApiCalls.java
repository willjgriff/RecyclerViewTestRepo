package com.github.willjgriff.playground.network.api.Etherchain;

import com.github.willjgriff.playground.network.endpoints.ApiEtherchain;
import com.github.willjgriff.playground.network.model.ethereum.Block;
import com.github.willjgriff.playground.network.model.ethereum.BlockCount;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

import static com.github.willjgriff.playground.network.api.ApiUris.Etherchain.URI_ETHERCHAIN;

/**
 * Created by Will on 28/03/2016.
 */
public class EtherchainApiCalls {

    private static ApiEtherchain getApiEtherchain(TypeAdapterFactory typeAdapterFactory) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();

        RxJavaCallAdapterFactory callAdapterFactory = RxJavaCallAdapterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URI_ETHERCHAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(callAdapterFactory)
                .build();

        return retrofit.create(ApiEtherchain.class);
    }

    public static Observable<BlockCount> blockCountCall() {
        return getApiEtherchain(new EtherchainTypeAdapterFactory(true)).totalBlockCount();
    }

    public static Observable<List<Block>> blockList(int offset, int count) {
        Type typeListBlock = new TypeToken<List<Block>>() {}.getType();
        return getApiEtherchain(new EtherchainTypeAdapterFactory(false, typeListBlock)).blocks(offset, count);
    }
}
