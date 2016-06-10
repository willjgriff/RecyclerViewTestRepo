package com.github.willjgriff.playground.dagger2.modules;

import android.app.Application;

import com.github.willjgriff.playground.dagger2.StackOverflowDagger;
import com.github.willjgriff.playground.network.api.ApiUris;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
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
        StackOverflowDagger stackOverflow = new StackOverflowDagger(retrofit);
        return stackOverflow;
    }
}
