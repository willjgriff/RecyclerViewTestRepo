package com.github.willjgriff.playground.mvp.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.willjgriff.playground.mvp.Presenter.MvpPresenter;

/**
 * Created by Will on 05/04/2016.
 */
public abstract class MvpFragment<PRESENTER extends MvpPresenter> extends Fragment implements MvpView {

    private PRESENTER mPresenter;

    protected abstract PRESENTER setPresenter();

    protected PRESENTER getPresenter() {
        if (mPresenter == null) {
            mPresenter = setPresenter();
        }
        return mPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() == null) {
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
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            //noinspection unchecked (Not ideal, need to understand generics better)
            mPresenter.setView(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.removeView();
    }

    /**
     * Pass the outState to the Presenter to store any data it needs
     * when being recreated after the system ends the View
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.saveState(outState);
    }
}
