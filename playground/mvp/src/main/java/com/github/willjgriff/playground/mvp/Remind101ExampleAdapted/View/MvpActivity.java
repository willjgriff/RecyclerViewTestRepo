package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.PresenterManager;

/**
 * Created by Will on 08/04/2016.
 * <p>
 * I will create an MvpLoadingActivity which extends this.
 */
public abstract class MvpActivity<PRESENTER extends Presenter> extends AppCompatActivity implements MvpView {

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
        if (savedInstanceState == null) {
            mPresenter = getPresenter();
        } else {
            mPresenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // noinspection unchecked
        getPresenter().bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(mPresenter, outState);
    }

}
