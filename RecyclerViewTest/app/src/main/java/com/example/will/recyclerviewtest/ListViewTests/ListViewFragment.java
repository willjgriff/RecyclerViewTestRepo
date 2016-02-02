package com.example.will.recyclerviewtest.ListViewTests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.will.recyclerviewtest.R;

/**
 * Created by Will on 01/02/2016.
 */
public class ListViewFragment extends Fragment {

    public static final String FRAGMENT_OTHER_TAG = "com.example.will.recyclerviewtest.RecyclerViewTest:FRAGMENT_OTHER_TAG";

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);

        mListView = (ListView) view.findViewById(R.id.fragment_list_view_list_view);

//        ArrayAdapter<String> namesAdapter =
//                new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, People.mNames);
        ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), People.getPeople());

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
