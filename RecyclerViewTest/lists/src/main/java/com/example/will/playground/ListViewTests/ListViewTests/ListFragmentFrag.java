package com.example.will.Playground.ListViewTests.ListViewTests;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.will.Playground.ListsActivity;

import java.util.List;

/**
 * Created by Will on 18/02/2016.
 */
public class ListFragmentFrag extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Person> people = getArguments().getParcelableArrayList(ListsActivity.FRAGMENT_ARGS);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), people);
        setListAdapter(listViewAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getContext(), "A bit of " + position, Toast.LENGTH_SHORT).show();
    }
}
