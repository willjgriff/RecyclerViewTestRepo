package com.github.willjgriff.playground.movies.Views;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpView;

/**
 * Created by Will on 11/04/2016.
 */
public interface MovieDetailsView extends MvpView {

    void setName(String title);

    void setPoster(String posterImage);
}
