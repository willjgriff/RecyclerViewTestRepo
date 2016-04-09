package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;

/**
 * This is weak. I can't work out how to have this abstract class implement an interface that
 * extends the RecyclerView.ViewHolder and therefore it won't be accepted when being passed
 * to RecyclerView.Adapter<VH>. For now this class won't have an interface, lame...
 * I may remove all interfaces from the MVP stuff. Not very SOLID though.
 */
public abstract class MvpViewHolder<P extends Presenter> extends RecyclerView.ViewHolder {
    protected P presenter;

    public MvpViewHolder(View itemView) {
        super(itemView);
    }

    public void bindPresenter(P presenter) {
        this.presenter = presenter;
        presenter.bindView(this);
    }

    public void unbindPresenter() {
        presenter = null;
    }
}
