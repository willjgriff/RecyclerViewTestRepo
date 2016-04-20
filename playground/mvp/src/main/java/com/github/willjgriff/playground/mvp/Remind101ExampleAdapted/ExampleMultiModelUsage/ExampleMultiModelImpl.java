package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleMultiModelUsage;

import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.lists.data.Person;

/**
 * Created by Will on 09/04/2016.
 */
public class ExampleMultiModelImpl implements ExampleMultiModel {

    private Person mPerson;
    private MovieListItem mMovieListItem;

    @Override
    public Person getPerson() {
        return mPerson;
    }

    @Override
    public MovieListItem getMovie() {
        return mMovieListItem;
    }

    @Override
    public String getId() {
        return "com.github.willjgriff.playground.mvp.Remind101ExampleAdapter.ExampleMultiModelUsage.ExampleMulitModelImpl;ID";
    }

    public void setPerson(Person person) {
        this.mPerson = person;
    }

    public void setMovie(MovieListItem movieListItem) {
        this.mMovieListItem = movieListItem;
    }
}
