package com.github.willjgriff.playground.network;

import com.github.willjgriff.playground.network.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;
import com.github.willjgriff.playground.network.utils.RetrofitUtils;

import retrofit.Call;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.ApiUris.StackOverflow.URI_STACK_OVERFLOW;

/**
 * Created by Will on 18/04/2016.
 */
public class StackOverflow {

    public static Call<StackOverflowQuestions> androidQuestionsCall() {
        Retrofit retrofit = RetrofitUtils.getRetrofit(URI_STACK_OVERFLOW, null, null);
        ApiStackOverflow apiStackOverflow = retrofit.create(ApiStackOverflow.class);
        return apiStackOverflow.loadQuestions("android");
    }
}
