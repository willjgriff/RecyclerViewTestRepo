package com.example.will.Playground.ListViewTests;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

/**
 * Created by Will on 10/02/2016.
 */
public class RecyclerViewDialogAdapter extends RecyclerViewAdapter {

    private RecyclerViewDialogListener mRecyclerViewDialogListener;

    public interface RecyclerViewDialogListener {
        void recyclerViewItemClick(Person person);
    }

    public RecyclerViewDialogAdapter(RecyclerViewDialogListener recyclerViewDialogListener, List<Person> mPeople, @LayoutRes int layoutItem) {
        super(mPeople, layoutItem);
        mRecyclerViewDialogListener = recyclerViewDialogListener;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewDialogListener.recyclerViewItemClick(mPeople.get(position));
            }
        });
    }
}
