package com.github.willjgriff.playground.signup;

import com.github.willjgriff.playground.mvp.RxMvp.RxPresenter.RxPresenter;
import com.github.willjgriff.playground.network.model.ethereum.Block;

import java.util.List;

import rx.observables.ConnectableObservable;

/**
 * Created by Will on 02/05/2016.
 */
public interface SignupPresenter extends RxPresenter<SignupView> {

    ConnectableObservable<List<Block>> signup(String username, String password);
}
