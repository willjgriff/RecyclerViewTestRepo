package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Helper;

import android.support.annotation.StringRes;

import com.github.willjgriff.playground.PlaygroundApplication;

/**
 * Created by Will on 20/04/2016.
 * <p>
 * Use the application context in the helper to prevent any risk of leaks.
 * Anything that requires the Activity Context should be doable in the View itself.
 */
public class BasePresenterHelper implements PresenterHelper {

    @Override
    public String getString(@StringRes int stringId) {
        return PlaygroundApplication.app().getString(stringId);
    }
}
