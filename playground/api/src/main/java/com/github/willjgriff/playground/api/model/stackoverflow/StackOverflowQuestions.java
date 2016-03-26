package com.github.willjgriff.playground.api.model.stackoverflow;

import java.util.List;

/**
 * Created by Will on 26/03/2016.
 */
public class StackOverflowQuestions {

    public List<StackOverflowQuestion> items;

    public List<StackOverflowQuestion> getQuestions() {
        return items;
    }
}
