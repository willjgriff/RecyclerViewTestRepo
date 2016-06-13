package com.github.willjgriff.playground.lists.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Will on 12/06/2016.
 */

public abstract class AbstractPeopleViewHolder<MODEL> extends RecyclerView.ViewHolder {

    public AbstractPeopleViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(MODEL model);
}
