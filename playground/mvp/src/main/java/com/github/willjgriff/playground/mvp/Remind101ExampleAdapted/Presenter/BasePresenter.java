package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Found this MVP structure here: http://engineering.remind.com/android-code-that-scales/
 * As stated in the MyBaseMvpPresenter of the MyAttempt package I tried to create my own,
 * but I started copying this one which is clearly more refined. I would have liked to have
 * completely created my own but, I'm still a baby, I'll be capable one day. The Views
 * are pretty much my own and I've added to to the adapters. Shame it requires Guava for a Cache Map...
 */
public abstract class BasePresenter<MODEL, VIEW> implements Presenter<MODEL, VIEW> {
    protected MODEL mModel;
    private WeakReference<VIEW> mView;

    @Override
    public void setModel(MODEL model) {
        resetState();
        this.mModel = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    @Override
    public void bindView(@NonNull VIEW view) {
        this.mView = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    @Override
    public void unbindView() {
        this.mView = null;
    }

    protected VIEW view() {
        if (mView == null) {
            return null;
        } else {
            return mView.get();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null && mModel != null;
    }

}
