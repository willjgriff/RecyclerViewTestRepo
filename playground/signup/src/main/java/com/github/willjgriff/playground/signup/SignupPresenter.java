package com.github.willjgriff.playground.signup;

import com.github.willjgriff.playground.mvp.RxMvp.RxPresenter.RxPresenter;

import rx.observables.ConnectableObservable;

/**
 * Created by Will on 02/05/2016.
 */
public interface SignupPresenter extends RxPresenter<SignupView> {

    ConnectableObservable<?> signup(String username, String password);
}
