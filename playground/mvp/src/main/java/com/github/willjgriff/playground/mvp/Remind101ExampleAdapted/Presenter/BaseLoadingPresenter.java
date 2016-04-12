package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import android.support.annotation.NonNull;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpLoadingView;

/**
 * Created by Will on 11/04/2016.
 */
public abstract class BaseLoadingPresenter<MODEL, VIEW extends MvpLoadingView<MODEL>> extends BasePresenter<MODEL, VIEW>
    implements LoadingPresenter<MODEL, VIEW> {

    private boolean isLoadingData = false;

    @Override
    protected void updateView() {
        view().showData(mModel);
    }

    @Override
    public void bindView(@NonNull VIEW view) {
        super.bindView(view);
        if (mModel == null && !isLoadingData) {
            view().showLoading();
            loadDataModel();
        }
    }

    public abstract void loadDataModel();

    protected void setLoading(boolean loading) {
        isLoadingData = loading;
    }

}
