package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.ExampleUsage;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpActivity;

/**
 * Created by Will on 08/04/2016.
 */
public class ExampleActivity extends MvpActivity<ExamplePresenterImpl> implements ExampleActivityView {

    @Override
    protected ExamplePresenterImpl setPresenter() {
        return new ExamplePresenterImpl();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Do normal view stuff, like toolbars and that.
    }

    @Override
    public void setMovieTitle(String title) {

    }
}
