package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleMultiModelUsage;

import com.github.willjgriff.playground.api.model.Entity;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.lists.data.Person;

/**
 * Created by Will on 09/04/2016.
 */
public interface ExampleMultiModel extends Entity {

    Person getPerson();

    Movie getMovie();

    void setPerson(Person person);

    void setMovie(Movie movie);
}
