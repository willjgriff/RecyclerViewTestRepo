package com.github.willjgriff.playground.coord;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.will.Playground.R;

import com.github.willjgriff.playground.lists.adapters.PeopleRecyclerViewAdapter;
import com.github.willjgriff.playground.lists.data.People;

/**
 * Created by Will on 04/02/2016.
 */
public class CoordToolbarActivity extends AppCompatActivity {

    private RecyclerView mCoordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coord_toolbar_hide);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.fragment_fab_coordinator);

        mCoordList = (RecyclerView) findViewById(R.id.fragment_coord_fab_recycler_view);
        mCoordList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCoordList.setLayoutManager(linearLayoutManager);
        mCoordList.setAdapter(new PeopleRecyclerViewAdapter(this, People.getPeople()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.fragment_coord_fab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.fragment_coord_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, R.string.fragment_coord_snackbar, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
