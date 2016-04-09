package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleMultiModelUsage;

import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.lists.data.Person;

/**
 * Created by Will on 09/04/2016.
 */
public class ExampleMultiModelImpl implements ExampleMultiModel {

    private Person mPerson;
    private Movie mMovie;

    @Override
    public Person getPerson() {
        return mPerson;
    }

    @Override
    public Movie getMovie() {
        return mMovie;
    }

    @Override
    public String getId() {
        return "com.github.willjgriff.playground.mvp.Remind101ExampleAdapter.ExampleMultiModelUsage.ExampleMulitModelImpl;ID";
    }

    public void setPerson(Person person) {
        this.mPerson = person;
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
    }
}
