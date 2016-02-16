package com.example.will.Playground.ListViewTests.ListViewTests;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.will.Playground.ListViewTests.ListViewTests.RecyclerViewDialogAdapter.RecyclerViewDialogListener;
import com.example.will.Playground.R;

/**
 * Created by Will on 09/02/2016.
 */
public class RecyclerViewDialogFragment extends DialogFragment implements RecyclerViewDialogListener {

    RecyclerViewFragmentListener mRecyclerViewFragmentListener;

    public interface RecyclerViewFragmentListener {
        void itemSelected(Person person);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_dialog, container);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recycler_view_dialog_recycler_view);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(new RecyclerViewDialogAdapter(this, People.getPeople(), R.layout.fragment_recycler_view_item_grid));

//      Remember AlertDialog allows you to set a custom title view!
        getDialog().setTitle("I AM DIALOG");

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mRecyclerViewFragmentListener = (RecyclerViewFragmentListener) activity;
    }

    @Override
    public void recyclerViewItemClick(Person person) {
        mRecyclerViewFragmentListener.itemSelected(person);
        dismiss();
    }
}
