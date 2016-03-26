package com.github.willjgriff.playground.lists.adapters;

import android.content.Context;
import android.view.View;

import com.github.willjgriff.playground.lists.data.Person;

import java.util.List;

/**
 * Created by Will on 22/03/2016.
 */
public class PeopleRecyclerViewWithClickAdapter extends PeopleRecyclerViewAdapter {

    RecyclerViewListener mRecyclerViewListener;

    public interface RecyclerViewListener {
        void recyclerViewItemClick(Person person, View transitionImage, View transitionName, View transitionAge);
    }

    public PeopleRecyclerViewWithClickAdapter(Context context, List<Person> mPeople, RecyclerViewListener viewListener) {
        super(context, mPeople);
        mRecyclerViewListener = viewListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.mLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewListener.recyclerViewItemClick(mPeople.get(position), holder.mPersonImage, holder.mPersonName, holder.mPersonAge);
            }
        });
    }
}
