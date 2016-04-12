package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpListLoadingView;

import java.util.List;

/**
 * Created by Will on 08/04/2016.
 */
public abstract class BaseLoadingListPresenter<MODEL, VIEW extends MvpListLoadingView<MODEL>> extends BaseLoadingPresenter<List<MODEL>, VIEW>
        implements ListLoadingPresenter<MODEL, VIEW> {

    @Override
    protected void updateView() {
        if (mModel.size() == 0) {
            view().showEmpty();
        } else {
            view().showData(mModel);
        }
    }
}
