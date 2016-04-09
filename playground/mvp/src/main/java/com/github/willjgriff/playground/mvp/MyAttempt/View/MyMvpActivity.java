package com.github.willjgriff.playground.mvp.MyAttempt.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.willjgriff.playground.mvp.MyAttempt.Presenter.MyBaseMvpPresenter;

/**
 * Created by Will on 04/04/2016.
 */
public abstract class MyMvpActivity<PRESENTER extends MyBaseMvpPresenter> extends AppCompatActivity implements MyMvpView {

    private PRESENTER mPresenter;

    protected abstract PRESENTER setPresenter();

    protected PRESENTER getPresenter() {
        if (mPresenter == null) {
            mPresenter = setPresenter();
        }
        return mPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setPresenter() == null) {
            Log.e("No Presenter Set", "No presenter set. Override setPresenter() returning a valid presenter");
        }
        if (savedInstanceState != null) {
            getPresenter().loadState(savedInstanceState);
        }
    }

    /**
     * Putting setView and removeView in onResume and onPause means we will reload data every
     * time the Activity is resumed. We can change this in the future depending on the desired
     * behaviour of the app (We could keep this and cache data in the activity or move these
     * / add more protected methods from the Presenter, to onCreate onStart onStop or onDestroy).
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            //noinspection unchecked (Not ideal, need to understand generics better)
            mPresenter.setView(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.removeView();
    }

    /**
     * Pass the outState to the Presenter to store any data it needs
     * when being recreated after the system ends the View
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }
}
