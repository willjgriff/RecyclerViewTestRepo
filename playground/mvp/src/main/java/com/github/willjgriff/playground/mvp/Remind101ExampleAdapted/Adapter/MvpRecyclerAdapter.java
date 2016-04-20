package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.github.willjgriff.playground.network.model.Entity;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpViewHolder;

import java.util.HashMap;
import java.util.Map;

public abstract class MvpRecyclerAdapter<MODEL extends Entity, PRESENTER extends Presenter, VIEWHOLDER extends MvpViewHolder<PRESENTER>> extends RecyclerView.Adapter<VIEWHOLDER> {
    protected final Map<Object, PRESENTER> mPresenters;

    public MvpRecyclerAdapter() {
        mPresenters = new HashMap<>();
    }

    @NonNull protected PRESENTER getPresenter(@NonNull MODEL model) {
        System.err.println("Getting presenter for item " + getModelId(model));
        return mPresenters.get(getModelId(model));
    }

    @NonNull protected abstract PRESENTER createPresenter(@NonNull MODEL model);

    @NonNull protected abstract Object getModelId(@NonNull MODEL model);

    @Override
    public void onViewRecycled(VIEWHOLDER holder) {
        super.onViewRecycled(holder);
        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(VIEWHOLDER holder) {
        // Sometimes, if animations are running on the itemView's children, the RecyclerView won't
        // be able to recycle the view. We should still unbind the presenter.
        holder.unbindPresenter();
        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onBindViewHolder(VIEWHOLDER holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }

    protected abstract MODEL getItem(int position);
}
