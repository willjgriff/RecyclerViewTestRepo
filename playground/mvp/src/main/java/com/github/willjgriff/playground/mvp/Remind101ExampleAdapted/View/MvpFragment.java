package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.PresenterManager;

/**
 * Created by Will on 09/04/2016.
 * <p>
 * At some point I will create an abstract MvpLoadingFragment which implements {@link MvpLoadingView}
 * and an MvpListLoadingFragment which implements {@link MvpListLoadingView}
 */
public abstract class MvpFragment<PRESENTER extends Presenter> extends Fragment implements MvpView {

    private PRESENTER mPresenter;

    protected abstract PRESENTER createPresenter();

    protected PRESENTER getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        return mPresenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mPresenter = getPresenter();
        } else {
            mPresenter = PresenterManager.getInstance().restorePresenter(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // noinspection unchecked
        mPresenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PresenterManager.getInstance().savePresenter(mPresenter, outState);
    }

}
