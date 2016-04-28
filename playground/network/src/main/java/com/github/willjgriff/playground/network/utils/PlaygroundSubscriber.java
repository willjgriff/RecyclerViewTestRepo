package com.github.willjgriff.playground.network.utils;

import rx.Subscriber;

/**
 * Created by Will on 28/04/2016.
 */
public abstract class PlaygroundSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }
}
