package com.github.willjgriff.playground.network.dagger2.modules;

import android.app.Application;

import com.github.willjgriff.playground.network.api.ApiUris;
import com.github.willjgriff.playground.network.dagger2.retrofitapis.ProdStackOverflowDagger;
import com.github.willjgriff.playground.network.dagger2.retrofitapis.StackOverflowDagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Will on 11/05/2016.
 */
@Module
public class StackOverflowModule {

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10MB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .cache(cache)
            .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUris.StackOverflow.URI_STACK_OVERFLOW)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    StackOverflowDagger provideStackOverflow(Retrofit retrofit) {
        StackOverflowDagger stackOverflow = new ProdStackOverflowDagger(retrofit);
        return stackOverflow;
    }
}
