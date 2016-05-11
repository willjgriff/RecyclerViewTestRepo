package com.github.willjgriff.playground.dagger2;

import com.github.willjgriff.playground.network.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by Will on 11/05/2016.
 */
public class StackOverflowDagger {

    private ApiStackOverflow mApiStackOverflow;

    public StackOverflowDagger(Retrofit retrofit) {
        mApiStackOverflow = retrofit.create(ApiStackOverflow.class);
    }

    public Call<StackOverflowQuestions> androidQuestionsCall() {
        return mApiStackOverflow.loadQuestions("android");
    }
}
