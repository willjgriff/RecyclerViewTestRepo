package com.github.willjgriff.playground.api;

import com.github.willjgriff.playground.api.endpoints.StackOverflowApi;
import com.github.willjgriff.playground.api.endpoints.TheMovieDbApi;
import com.github.willjgriff.playground.api.model.movies.TopMovies;
import com.github.willjgriff.playground.api.model.stackoverflow.StackOverflowQuestions;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by Will on 28/03/2016.
 */
public class RetrofitCalls {

    public static Call<StackOverflowQuestions> stackOverflowQuestionsCall() {
        Retrofit retrofit = RetrofitUtils.getRetrofit("https://api.stackexchange.com", null, null);
        StackOverflowApi stackOverflowApi = retrofit.create(StackOverflowApi.class);
        Call<StackOverflowQuestions> call = stackOverflowApi.loadQuestions("android");
        return call;
    }

    public static Call<TopMovies> topMoviesCall() {
        OkHttpClient client = RetrofitUtils.getMoviesClientWithApiKey("4eeec5eabda1a778430eed3981449334");
        Retrofit retrofit = RetrofitUtils.getRetrofit("https://api.themoviedb.org", client, null);
        TheMovieDbApi movieApi = retrofit.create(TheMovieDbApi.class);
        Call<TopMovies> call = movieApi.listTopMovies();
        return call;
    }
}
