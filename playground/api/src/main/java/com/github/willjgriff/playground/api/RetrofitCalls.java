package com.github.willjgriff.playground.api;

import com.github.willjgriff.playground.api.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.api.endpoints.ApiTheMovieDb;
import com.github.willjgriff.playground.api.model.movies.MovieFull;
import com.github.willjgriff.playground.api.model.movies.MoviesConfig;
import com.github.willjgriff.playground.api.model.movies.TopMovies;
import com.github.willjgriff.playground.api.model.stackoverflow.StackOverflowQuestions;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.api.ApiUris.StackOverflow.URI_STACK_OVERFLOW;
import static com.github.willjgriff.playground.api.ApiUris.TheMovieDb.MOVIES_API_KEY;
import static com.github.willjgriff.playground.api.ApiUris.TheMovieDb.URI_THE_MOVIE_DB;

/**
 * Created by Will on 28/03/2016.
 */
public class RetrofitCalls {

    public static Call<StackOverflowQuestions> stackOverflowQuestionsCall() {
        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_STACK_OVERFLOW, null, null);
        ApiStackOverflow apiStackOverflow = retrofit.create(ApiStackOverflow.class);
        return apiStackOverflow.loadQuestions("android");
    }

    private static ApiTheMovieDb getTheMovieDbApi() {
        OkHttpClient client = RetrofitUtils.getMoviesClientWithApiKey(MOVIES_API_KEY);
        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_THE_MOVIE_DB, client, null);
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
