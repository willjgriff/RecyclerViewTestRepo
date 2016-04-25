package com.github.willjgriff.playground.mvp.RxMvp.RxPresenter;

import android.os.Bundle;

/**
 * Created by Will on 09/04/2016.
 */
public interface RxPresenter<VIEW> {

    void bindView(VIEW view);

    void unbindView();

    void saveInstanceState(Bundle saveState);

    void restoreInstanceState(Bundle restoreState);
}
