package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleMultiModelUsage;

import com.github.willjgriff.playground.network.model.Entity;
import com.github.willjgriff.playground.network.model.movies.MovieListItem;
import com.github.willjgriff.playground.lists.model.Person;

/**
 * Created by Will on 09/04/2016.
 */
public interface ExampleMultiModel extends Entity {

    Person getPerson();

    MovieListItem getMovie();

    void setPerson(Person person);

    void setMovie(MovieListItem movieListItem);
}
