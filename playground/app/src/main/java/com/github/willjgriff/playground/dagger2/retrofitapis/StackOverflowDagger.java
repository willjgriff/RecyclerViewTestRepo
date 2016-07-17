package com.github.willjgriff.playground.dagger2.retrofitapis;

import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import retrofit.Call;

/**
 * Created by Will on 17/07/2016.
 */
public interface StackOverflowDagger {

	Call<StackOverflowQuestions> androidQuestionsCall();
}
