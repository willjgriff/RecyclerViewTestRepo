package com.github.willjgriff.playground.api.model.stackoverflow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Will on 26/03/2016.
 */
public class StackOverflowQuestions {

    @SerializedName("items")
    List<StackOverflowQuestion> stackOverflowQuestions;

    public List<StackOverflowQuestion> getQuestions() {
        return stackOverflowQuestions;
    }
}
