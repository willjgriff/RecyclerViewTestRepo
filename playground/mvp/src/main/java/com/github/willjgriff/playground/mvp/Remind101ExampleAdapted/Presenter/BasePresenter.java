package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * Found this MVP structure here: http://engineering.remind.com/android-code-that-scales/
 * As stated in the MyBaseMvpPresenter of the MyAttempt package I tried to create my own,
 * but I started copying this one which is clearly more refined. I would have liked to have
 * completely created my own but, I'm still a baby, I'll be capable one day. The Views
 * are pretty much my own. Shame it requires Guava for a Cache Map, maybe we can remove it...
 */
public abstract class BasePresenter<MODEL, VIEW> implements Presenter<MODEL, VIEW> {
    protected MODEL model;
    private WeakReference<VIEW> view;

    @Override
    public void setModel(MODEL model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    @Override
    public void bindView(@NonNull VIEW view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    protected VIEW view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null && model != null;
    }
}
