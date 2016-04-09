package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import android.support.annotation.NonNull;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.LoadingView;

import java.util.List;

/**
 * Created by Will on 08/04/2016.
 */
public abstract class ListLoadingPresenter<MODEL, VIEW extends LoadingView<MODEL>> extends BasePresenter<List<MODEL>, VIEW> {

    boolean isLoadingData = false;

    @Override
    protected void updateView() {
        if (model.size() == 0) {
            view().showEmpty();
        } else {
            view().showData(model);
        }
    }

    @Override
    public void bindView(@NonNull VIEW view) {
        super.bindView(view);
        if (model == null && !isLoadingData) {
            view().showLoading();
        }
    }

    protected void setLoading(boolean loading) {
        isLoadingData = loading;
    }
}
