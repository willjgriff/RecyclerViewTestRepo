package com.example.will.recyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Will on 01/02/2016.
 */
public class RecyclerViewFragment extends Fragment {

    public static final String FRAGMENT_RECYCLER_VIEW_TAG = "com.example.will.recyclerviewtest.RecyclerViewTest:FRAGMENT_RECYCLER_VIEW_TAG";
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(People.getPeople());
        mRecyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }
}
