package com.github.willjgriff.playground.mvp.Presenter;

import android.os.Bundle;

import com.github.willjgriff.playground.mvp.View.MvpView;

/**
 * Created by Will on 04/04/2016.
 */
public abstract class MvpPresenter<VIEW extends MvpView> {

    private VIEW mView;

    public VIEW getView() {
        return mView;
    }

    /**
     * Called in MvpActivity onResume()
     */
    public void setView(VIEW view) {
        mView = view;
        onSetView();
    }

    /**
     * Called in MvpActivity onPause()
     */
    public void removeView() {
        if (mView != null) {
            mView = null;
        }
        onRemoveView();
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
