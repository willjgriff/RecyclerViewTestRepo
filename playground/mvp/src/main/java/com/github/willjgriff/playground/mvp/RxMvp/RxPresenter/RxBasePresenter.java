package com.github.willjgriff.playground.mvp.RxMvp.RxPresenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.github.willjgriff.playground.network.utils.PlaygroundSubscriber;

import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
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

    protected <M> void addSubscription(Observable<M> observable, PlaygroundSubscriber<M> subscriber) {
        subscriptions.add(observable
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    protected <M> ConnectableObservable<M> addConnectableSubscription(Observable<M> observable, PlaygroundSubscriber<M> subscriber) {
        ConnectableObservable<M> connectableObservable = observable
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .publish();

        subscriptions.add(connectableObservable
                .subscribe(subscriber));

        return connectableObservable;
    }
}
