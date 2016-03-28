package com.github.willjgriff.playground.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Will on 28/03/2016.
 */
public class RetrofitUtils {

    public static Retrofit getRetrofit(@NonNull String baseUrl, @Nullable OkHttpClient client, @Nullable GsonConverterFactory gsonConverterFactory) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl);

        if (gsonConverterFactory != null) {
            retrofitBuilder.addConverterFactory(gsonConverterFactory);
        } else {
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        }

        if (client != null) {
            retrofitBuilder.client(client);
        }

        return retrofitBuilder.build();
    }

    public static OkHttpClient getMoviesClientWithApiKey(final String apiKey) {
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl.Builder builder = originalRequest.httpUrl().newBuilder()
                        .addQueryParameter("api_key", apiKey);
                Request newRequest = originalRequest.newBuilder()
                        .url(builder.build())
                        .method(originalRequest.method(), originalRequest.body())
                        .build();

                return chain.proceed(newRequest);
            }
        };

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(requestInterceptor);

        return client;
    }
}
