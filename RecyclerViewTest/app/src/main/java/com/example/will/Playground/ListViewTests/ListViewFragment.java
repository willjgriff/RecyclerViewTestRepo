package com.example.will.Playground.ListViewTests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.will.Playground.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.will.Playground.MainActivity.FRAGMENT_ARGS;

/**
 * Created by Will on 01/02/2016.
 */
public class ListViewFragment extends Fragment {

    public static final String LIST_VIEW_FRAGMENT_TAG = "com.example.will.recyclerviewtest.RecyclerViewTest;LIST_VIEW_FRAGMENT_TAG";
    public static final String LIST_VIEW_FRAGMENT_PEOPLE_EXTRA = "com.example.will.recyclerviewtest.RecyclerViewTest;LIST_VIEW_FRAGMENT_PEOPLE_EXTRA";

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_layout, container, false);

        List<Person> people = new ArrayList<>();
        Bundle args = getArguments();
        if (args != null) {
            people = args.getParcelableArrayList(FRAGMENT_ARGS);
        }

        mListView = (ListView) view.findViewById(R.id.fragment_list_view_list_view);
//        ArrayAdapter<String> namesAdapter =
//                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, People.mNames);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), people);
        mListView.setAdapter(listViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Item of position" + String.valueOf(position), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        return view;
    }
}
