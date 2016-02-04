package com.example.will.Playground;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.will.Playground.ListViewTests.ListViewFragment;
import com.example.will.Playground.ListViewTests.People;
import com.example.will.Playground.ListViewTests.Person;
import com.example.will.Playground.ListViewTests.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ToolbarActivity {

    public static final String FRAGMENT_ARGS = "com.example.will.playground;FRAGMENT_ARGS";

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_change_fragment_recycler_view_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new RecyclerViewFragment(), People.getPeople());
            }
        });

        findViewById(R.id.activity_main_change_fragment_list_view_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment(new ListViewFragment(), People.getPeople());
            }
        });
    }

    private void openFragment(Fragment fragment, List<Person> data) {

        if (data != null) {
            Bundle fragmentBundle = new Bundle();
            ArrayList<Person> parcelables = new ArrayList<>(data);
            fragmentBundle.putParcelableArrayList(FRAGMENT_ARGS, parcelables);
            fragment.setArguments(fragmentBundle);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment, fragment)
                .commit();
    }
}
