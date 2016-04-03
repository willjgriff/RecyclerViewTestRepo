package com.github.willjgriff.playground.api.endpoints;

import com.github.willjgriff.playground.api.model.stackoverflow.StackOverflowQuestions;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Will on 26/03/2016.
 */
public interface ApiStackOverflow {

    @GET("2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);
}
