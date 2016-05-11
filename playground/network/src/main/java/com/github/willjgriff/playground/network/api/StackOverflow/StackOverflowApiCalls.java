package com.github.willjgriff.playground.network.api.StackOverflow;

import com.github.willjgriff.playground.network.endpoints.ApiStackOverflow;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.network.api.ApiUris.StackOverflow.URI_STACK_OVERFLOW;

/**
 * Created by Will on 18/04/2016.
 */
public class StackOverflowApiCalls {

    public static Call<StackOverflowQuestions> androidQuestionsCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URI_STACK_OVERFLOW)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiStackOverflow apiStackOverflow = retrofit.create(ApiStackOverflow.class);
        return apiStackOverflow.loadQuestions("android");
    }
}
