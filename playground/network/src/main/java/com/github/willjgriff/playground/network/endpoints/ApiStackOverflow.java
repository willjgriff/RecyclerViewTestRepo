package com.github.willjgriff.playground.network.endpoints;

import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Will on 26/03/2016.
 */
public interface ApiStackOverflow {

    @GET("2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}
