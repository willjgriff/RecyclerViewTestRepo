package com.github.willjgriff.playground.dagger2.retrofitapis;

import com.github.willjgriff.playground.network.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit.Call;
import retrofit.Retrofit;

/**
 * Created by Will on 11/05/2016.
 */
public class MockStackOverflowDagger implements StackOverflowDagger {

    private ApiStackOverflow mApiStackOverflow;

    public ProdStackOverflowDagger(Retrofit retrofit) {
        mApiStackOverflow = retrofit.create(ApiStackOverflow.class);
    }

    @Override
    public Call<StackOverflowQuestions> androidQuestionsCall() {
        return mApiStackOverflow.loadQuestions("android");
    }
}
