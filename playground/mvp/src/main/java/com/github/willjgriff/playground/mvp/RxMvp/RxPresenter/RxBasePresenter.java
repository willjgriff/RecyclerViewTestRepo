package com.github.willjgriff.playground.mvp.RxMvp.RxPresenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Found bits of this MVP structure here: http://engineering.remind.com/android-code-that-scales/
 * See {@link com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.BasePresenter}
 * for original adaptation.
 */
public abstract class RxBasePresenter<VIEW> implements RxPresenter<VIEW> {
    protected CompositeSubscription subscriptions;
    private WeakReference<VIEW> mView;

    @Override
    public void bindView(@NonNull VIEW view) {
        this.mView = new WeakReference<>(view);
        subscriptions = new CompositeSubscription();
    }

    @Override
    public void unbindView() {
        this.mView = null;
        subscriptions.unsubscribe();
    }

    @Override
    public void saveInstanceState(Bundle saveState) {
        // Use to save the state of the presenter
    }

    @Override
    public void restoreInstanceState(Bundle restoreState) {
        // Use to restore the state of the presenter
    }

    protected VIEW view() {
        if (mView == null) {
            return null;
        } else {
            return mView.get();
        }
    }

    protected void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    protected <M> void addSubscription(Observable<M> observable, Action1<M> action1) {
        observable
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }
}
