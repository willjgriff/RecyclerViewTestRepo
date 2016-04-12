package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;

/**
 * This is weak. I can't work out how to have this abstract class implement an interface that
 * extends the RecyclerView.ViewHolder and therefore it won't be accepted when being passed
 * to RecyclerView.Adapter<VH>. For now this class won't have an interface. Not very SOLID.
 */
public abstract class MvpViewHolder<PRESENTER extends Presenter> extends RecyclerView.ViewHolder {
    protected PRESENTER mPresenter;

    public MvpViewHolder(View itemView) {
        super(itemView);
    }

    public void bindPresenter(PRESENTER presenter) {
        this.mPresenter = presenter;
        presenter.bindView(this);
    }

    public void unbindPresenter() {
        mPresenter = null;
    }

    public void setViewHolderItemClickListener(final OnViewHolderItemClickListener clickListener){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.viewHolderItemClick(v, getAdapterPosition());
            }
        });
    }

    public interface OnViewHolderItemClickListener {
        void viewHolderItemClick(View v, int position);
    }
}
