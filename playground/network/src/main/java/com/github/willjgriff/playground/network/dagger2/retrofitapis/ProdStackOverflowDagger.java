package com.github.willjgriff.playground.network.dagger2.retrofitapis;

import com.github.willjgriff.playground.network.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Will on 11/05/2016.
 */
public class ProdStackOverflowDagger implements StackOverflowDagger {

    private ApiStackOverflow mApiStackOverflow;

    public ProdStackOverflowDagger(Retrofit retrofit) {
        mApiStackOverflow = retrofit.create(ApiStackOverflow.class);
    }

    @Override
    public Call<StackOverflowQuestions> androidQuestionsCall() {
        return mApiStackOverflow.loadQuestions("android");
    }
}
