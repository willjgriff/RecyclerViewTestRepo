package com.github.willjgriff.playground.network.api.TheMovieDb;

import com.github.willjgriff.playground.network.endpoints.ApiTheMovieDb;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.api.ApiUris.TheMovieDb.MOVIES_API_KEY;
import static com.github.willjgriff.playground.network.api.ApiUris.TheMovieDb.URI_THE_MOVIE_DB;
import static com.github.willjgriff.playground.network.model.movies.MovieListItem.RELEASE_DATE_FORMAT;

/**
 * Created by Will on 18/04/2016.
 */
public class TheMovieDbApi {

    public static ApiTheMovieDb getTheMovieDbApi() {
        // The gson enables automatic conversion from the specified date format to a date object.
        Gson gson = new GsonBuilder().setDateFormat(RELEASE_DATE_FORMAT).create();
        OkHttpClient client = getMoviesClientWithApiKey(MOVIES_API_KEY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URI_THE_MOVIE_DB)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ApiTheMovieDb.class);
    }

    private static OkHttpClient getMoviesClientWithApiKey(final String apiKey) {
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
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
