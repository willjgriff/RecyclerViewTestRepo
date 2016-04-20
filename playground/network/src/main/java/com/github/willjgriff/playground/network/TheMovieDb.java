package com.github.willjgriff.playground.network;

import com.github.willjgriff.playground.network.endpoints.ApiTheMovieDb;
import com.github.willjgriff.playground.network.model.movies.MovieFull;
import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.network.model.movies.TopMovies;
import com.github.willjgriff.playground.network.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.ApiUris.TheMovieDb.MOVIES_API_KEY;
import static com.github.willjgriff.playground.network.ApiUris.TheMovieDb.URI_THE_MOVIE_DB;
import static com.github.willjgriff.playground.network.model.movies.MovieListItem.RELEASE_DATE_FORMAT;

/**
 * Created by Will on 18/04/2016.
 */
public class TheMovieDb {

    private static ApiTheMovieDb getTheMovieDbApi() {
        Gson gson = new GsonBuilder().setDateFormat(RELEASE_DATE_FORMAT).create();
        OkHttpClient client = RetrofitUtils.getMoviesClientWithApiKey(MOVIES_API_KEY);
        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_THE_MOVIE_DB, client, GsonConverterFactory.create(gson));
        return retrofit.create(ApiTheMovieDb.class);
    }

    public static Call<TopMovies> topMoviesCall() {
        return getTheMovieDbApi().listTopMovies();
    }

    public static Call<MoviesConfig> moviesConfigCall() {
        return getTheMovieDbApi().imageConfig();
    }

    public static Call<MovieFull> movieDetailsCall(String movieId) {
        return getTheMovieDbApi().movie(movieId);
    }
}
