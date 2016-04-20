package com.github.willjgriff.playground.ethereum.views;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpView;

/**
 * Created by Will on 18/04/2016.
 */
public interface BlockView extends MvpView {

    void setBlockCount(long count);

    void setBlockHash(String hash);
}
