package com.github.willjgriff.playground.mvp.Presenter;

import android.os.Bundle;

import com.github.willjgriff.playground.mvp.View.MvpView;

/**
 * Created by Will on 05/04/2016.
 */
public interface MvpPresenterUnused<VIEW extends MvpView> {

    VIEW getView();

    void setView(VIEW view);

    void removeView();

    void saveState(Bundle outState);
}
