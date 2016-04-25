package com.github.willjgriff.playground.mvp.MyFirstAttempt.Presenter;

import android.os.Bundle;

import com.github.willjgriff.playground.mvp.MyFirstAttempt.View.MyMvpView;

/**
 * Created by Will on 04/04/2016.
 * <p>
 * I came up with this MVP structure. But after some research I found a few much better examples,
 * one specifically that integrates MVP into Collection Views (Remind101) that I expect I
 * will use. Unfortunately it requires Guava...
 */
public abstract class MyBaseMvpPresenter<VIEW extends MyMvpView> implements MyMvpPresenter<VIEW> {

    private VIEW mView;

    public VIEW getView() {
        return mView;
    }

    /**
     * Called in MyMvpActivity onResume()
     */
    public void setView(VIEW view) {
        mView = view;
        onSetView();
    }

    /**
     * Called in MyMvpActivity onPause()
     */
    public void removeView() {
        onRemoveView();
        mView = null;
    }

    protected void onSetView() {
        // Generally start Api requests here and setup the View
    }

    protected void onRemoveView() {
        // Generally cancel Api requests here
    }

    /**
     * A couple of the examples I've been using have used the Bundle in their
     * Presenters. For ease I will too but I wonder if the Presenter should have any
     * Android dependencies. In future I may use an alternative to the Bundle.
     */
    public void saveState(Bundle outState) {
        // Put in the outState anything we wish to save in
        // the presenter in case the system ends the View.
    }

    public void loadState(Bundle inState) {
        // Get from the inState anything we saved and
        // restore it to the presenter.
    }
}
