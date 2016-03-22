package com.playground.will.coord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.will.Playground.R;

import com.playground.will.lists.adapters.PeopleRecyclerViewAdapter;
import com.playground.will.lists.data.People;

/**
 * Created by Will on 13/03/2016.
 */
public class CoordToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coord_toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fragment_coord_toolbar_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        PeopleRecyclerViewAdapter adapter = new PeopleRecyclerViewAdapter(this, People.getPeople(), R.layout.adapter_recycler_view_item);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.fragment_coord_toolbar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.fragment_coord_toolbar_collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.fragment_coord_toolbar_title));

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.fragment_coord_toolbar_coordinator);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fragment_coord_toolbar_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, R.string.fragment_coord_snackbar, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
