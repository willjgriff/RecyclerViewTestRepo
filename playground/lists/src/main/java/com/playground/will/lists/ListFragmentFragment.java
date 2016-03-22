package com.playground.will.lists;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import com.playground.will.lists.adapters.ListViewAdapter;
import com.playground.will.lists.data.Person;

/**
 * Created by Will on 18/02/2016.
 */
public class ListFragmentFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Person> people = getArguments().getParcelableArrayList(ListsFragment.FRAGMENT_ARGS);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), people);
        setListAdapter(listViewAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getContext(), "A bit of " + position, Toast.LENGTH_SHORT).show();
    }
}
