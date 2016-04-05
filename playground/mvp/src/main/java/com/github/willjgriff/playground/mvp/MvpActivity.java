package com.github.willjgriff.playground.mvp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Will on 04/04/2016.
 */
public abstract class MvpActivity<PRESENTER extends MvpPresenter> extends AppCompatActivity {

    private PRESENTER mPresenter;

    protected abstract PRESENTER setPresenter();

    protected PRESENTER getPresenter() {
        if (mPresenter == null) {
            mPresenter = setPresenter();
        }
        return mPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }
}
