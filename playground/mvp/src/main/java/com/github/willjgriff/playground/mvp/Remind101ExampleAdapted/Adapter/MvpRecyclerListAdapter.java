package com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Adapter;

import android.view.View;
import android.view.ViewGroup;

import com.github.willjgriff.playground.network.model.Entity;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.Presenter.Presenter;
import com.github.willjgriff.playground.mvp.Remind101ExampleAdapted.View.MvpViewHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * I should extend this to include a loading footer.
 */
public abstract class MvpRecyclerListAdapter<MODEL extends Entity, PRESENTER extends Presenter, VIEWHOLDER extends MvpViewHolder<PRESENTER>> extends MvpRecyclerAdapter<MODEL, PRESENTER, VIEWHOLDER> {
    private final List<MODEL> mModels;
    private OnItemClickListener<MODEL> mItemClickListener;

    public MvpRecyclerListAdapter() {
        mModels = new ArrayList<>();
    }

    public void clearAndAddAll(Collection<MODEL> data) {
        mModels.clear();
        mPresenters.clear();

        for (MODEL item : data) {
            addInternal(item);
        }

        notifyDataSetChanged();
    }

    public void addAll(Collection<MODEL> data) {
        for (MODEL item : data) {
            addInternal(item);
        }

        int addedSize = data.size();
        int oldSize = mModels.size() - addedSize;
        notifyItemRangeInserted(oldSize, addedSize);
    }

    public void addItem(MODEL item) {
        addInternal(item);
        notifyItemInserted(mModels.size());
    }

    public void updateItem(MODEL item) {
        Object modelId = getModelId(item);

        // Swap the model
        int position = getItemPosition(item);
        if (position >= 0) {
            mModels.remove(position);
            mModels.add(position, item);
        }

        // Swap the presenter
        PRESENTER existingPresenter = mPresenters.get(modelId);
        if (existingPresenter != null) {
            existingPresenter.setModel(item);
        }

        if (position >= 0) {
            notifyItemChanged(position);
        }
    }

    public void removeItem(MODEL item) {
        int position = getItemPosition(item);
        if (position >= 0) {
            mModels.remove(item);
        }
        mPresenters.remove(getModelId(item));

        if (position >= 0) {
            notifyItemRemoved(position);
        }
    }

    private int getItemPosition(MODEL item) {
        Object modelId = getModelId(item);

        int position = -1;
        for (int i = 0; i < mModels.size(); i++) {
            MODEL model = mModels.get(i);
            if (getModelId(model).equals(modelId)) {
                position = i;
                break;
            }
        }
        return position;
    }

    private void addInternal(MODEL item) {
        System.err.println("Adding item " + getModelId(item));
        mModels.add(item);
        mPresenters.put(getModelId(item), createPresenter(item));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    protected MODEL getItem(int position) {
        return mModels.get(position);
    }

    @Override
    public VIEWHOLDER onCreateViewHolder(ViewGroup parent, int viewType) {
        VIEWHOLDER viewholder = setViewHolder(parent, viewType);
        viewholder.setViewHolderItemClickListener(new MvpViewHolder.OnViewHolderItemClickListener() {
            @Override
            public void viewHolderItemClick(View v, int position) {
                mItemClickListener.onItemClicked(v, getItem(position));
            }
        });
        return viewholder;
    }

    protected abstract VIEWHOLDER setViewHolder(ViewGroup parent, int viewType);

    public void setOnClickListener(OnItemClickListener<MODEL> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface OnItemClickListener<MODEL> {
        void onItemClicked(View view, MODEL model);
    }
}
