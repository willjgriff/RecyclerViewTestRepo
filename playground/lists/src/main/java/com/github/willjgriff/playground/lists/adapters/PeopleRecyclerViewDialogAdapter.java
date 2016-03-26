package com.github.willjgriff.playground.lists.adapters;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.github.willjgriff.playground.lists.data.Person;

import java.util.List;

/**
 * Created by Will on 10/02/2016.
 * </p>
 * I would probably create a new recyclerView here instead
 * of extending one but for testing purposes this is acceptable.
 */
public class PeopleRecyclerViewDialogAdapter extends PeopleRecyclerViewAdapter {

    private RecyclerViewDialogListener mRecyclerViewDialogListener;

    public interface RecyclerViewDialogListener {
        void recyclerViewItemClick(Person person);
    }

    public PeopleRecyclerViewDialogAdapter(RecyclerViewDialogListener recyclerViewDialogListener, List<Person> mPeople, @LayoutRes int layoutItem) {
        super(null, mPeople, layoutItem);
        mRecyclerViewDialogListener = recyclerViewDialogListener;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.mLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewDialogListener.recyclerViewItemClick(mPeople.get(position));
            }
        });
    }
}
