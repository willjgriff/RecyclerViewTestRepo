package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleUsage;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.Movie;
import com.github.willjgriff.playground.lists.data.Person;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Will on 08/04/2016.
 */
public class ExamplePresenterImpl extends BasePresenter<Map<String, Object>, ExampleActivityView> implements ExamplePresenter {

    public static final String KEY_MOVIE_MODEL = "com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleUsage.ExamplePresenterImpl;KEY_MOVIE_MODEL";
    public static final String KEY_PERSON_MODEL = "com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleUsage.ExamplePresenterImpl;KEY_PERSON_MODEL";

    Map<String, Object> mModels = new HashMap<>();

    public ExamplePresenterImpl() {
        // If you must have multiple Models (eg waiting for multiple Api Responses at the same time) do it
        // like this and set the modelSize() to the number of Models we're expecting. Most undesirable though.
        // I'm unsure if I can force one Model per View in the Model structure. If I can, I'll remove this.
        Movie movie = new Movie();
        mModels.put(KEY_MOVIE_MODEL, movie);
        setModel(mModels);

        Person person = new Person("Lantuash", "20", R.drawable.red_panda);
        mModels.put(KEY_PERSON_MODEL, person);
        setModel(mModels);
    }

    @Override
    protected void updateView() {
        // Use data from model here.
        view().setMovieTitle(((Movie) model.get(KEY_MOVIE_MODEL)).getTitle());
    }
}
