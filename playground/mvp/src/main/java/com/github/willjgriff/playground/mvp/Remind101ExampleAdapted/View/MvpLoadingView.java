package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

/**
 * Created by Will on 11/04/2016.
 */
public interface MvpLoadingView<MODEL> extends MvpView {

    void showEmpty();

    void showLoading();

    void showData(MODEL model);
}
