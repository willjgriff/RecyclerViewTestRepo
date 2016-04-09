package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

/**
 * Created by Will on 09/04/2016.
 */
public interface Presenter<MODEL, VIEW> {

    void setModel(MODEL model);

    void bindView(VIEW view);

    void unbindView();
}
