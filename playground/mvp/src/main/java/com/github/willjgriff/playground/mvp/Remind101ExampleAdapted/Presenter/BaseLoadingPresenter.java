package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import android.support.annotation.NonNull;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpLoadingSingleView;

/**
 * Created by Will on 11/04/2016.
 */
public abstract class BaseLoadingPresenter<MODEL, VIEW extends MvpLoadingSingleView<MODEL>> extends BasePresenter<MODEL, VIEW> {

    boolean isLoadingData = true;

    @Override
    protected void updateView() {
        if (model == null) {
            view().showEmpty();
        } else {
            view().showData(model);
        }
    }

    @Override
    public void bindView(@NonNull VIEW view) {
        super.bindView(view);
        if (model == null && isLoadingData) {
            view().showLoading();
            loadDataModel();
        }
    }

    protected abstract void loadDataModel();

    protected void setLoading(boolean loading) {
        isLoadingData = loading;
    }

}
