package com.github.willjgriff.playground.network.model.stackoverflow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Will on 26/03/2016.
 */
public class StackOverflowQuestions extends RealmObject {

    @PrimaryKey
    private int id;

    @SerializedName("items")
    private RealmList<StackOverflowQuestion> stackOverflowQuestions;

    public RealmList<StackOverflowQuestion> getStackOverflowQuestions() {
        return stackOverflowQuestions;
    }

    public void setStackOverflowQuestions(RealmList<StackOverflowQuestion> soQuestions) {
        this.stackOverflowQuestions = soQuestions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
