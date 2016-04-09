package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

import java.util.List;

/**
 * Created by Will on 08/04/2016.
 */
public interface LoadingView<MODEL> extends MvpView {

    void showEmpty();

    void showLoading();

    void showData(List<MODEL> model);

}
