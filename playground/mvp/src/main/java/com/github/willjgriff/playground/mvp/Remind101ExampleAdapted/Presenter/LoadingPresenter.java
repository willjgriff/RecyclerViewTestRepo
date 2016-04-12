package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

/**
 * Created by Will on 11/04/2016.
 */
public interface LoadingPresenter<MODEL, VIEW> extends Presenter<MODEL, VIEW> {

    void loadDataModel();
}
