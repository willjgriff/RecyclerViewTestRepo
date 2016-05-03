package com.github.willjgriff.playground.signup;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpView;

/**
 * Created by Will on 02/05/2016.
 */
public interface SignupView extends MvpView {

    void signupEnabled(boolean enabled);
}
