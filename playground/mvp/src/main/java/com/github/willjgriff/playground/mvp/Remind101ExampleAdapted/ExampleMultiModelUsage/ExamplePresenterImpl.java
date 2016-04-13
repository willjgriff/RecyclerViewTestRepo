package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleMultiModelUsage;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.api.model.movies.MovieListItem;
import com.github.willjgriff.playground.lists.data.Person;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter;

/**
 * Created by Will on 08/04/2016.
 */
public class ExamplePresenterImpl extends BasePresenter<ExampleMultiModel, ExampleActivityView> implements ExamplePresenter {

    private ExampleMultiModel multiModel = new ExampleMultiModelImpl();

    public ExamplePresenterImpl() {
        // If you must have multiple Models (eg waiting for multiple Api Responses at the same time)
        // maybe do it like this. It's not pretty though, there must be a better way, RxJava, RxAndroid...
        // Hopefully I can force one Model per View in the Model structure. If I can, there's no need for this.

        // Imagine this is an Asynchronous Api Request.
        MovieListItem movieListItem = new MovieListItem();
        multiModel.setMovie(movieListItem);
        if (modelComplete()) {
            setModel(multiModel);
        }

        // Imagine this is a concurrent Asynchronous Api Request.
        Person person = new Person("Lantuash", "20", R.drawable.red_panda);
        multiModel.setPerson(person);
        if (modelComplete()) {
            setModel(multiModel);
        }
    }

    @Override
    protected void updateView() {
        view().setMovieTitle((mModel.getMovie().getTitle()));
        view().setPersonName((mModel.getPerson().mName));
    }

    private boolean modelComplete() {
        return multiModel.getPerson() != null && multiModel.getMovie() != null;
    }
}
