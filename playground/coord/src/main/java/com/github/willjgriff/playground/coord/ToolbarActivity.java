package com.github.willjgriff.playground.coord;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.PeopleAdapter;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;
import com.github.willjgriff.playground.lists.model.data.People;

import java.util.List;

/**
 * Created by Will on 04/02/2016.
 */
public class ToolbarActivity extends AppCompatActivity implements PersonViewHolder.PersonItemListener {

    private RecyclerView mCoordList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coord_toolbar_hide);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.fragment_fab_coordinator);

        findViewById(R.id.fragment_coord_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, R.string.fragment_coord_snackbar, Snackbar.LENGTH_SHORT).show();
            }
        });

        setupPeopleList();
        setupToolbar();
    }

    private void setupPeopleList() {
        mCoordList = (RecyclerView) findViewById(R.id.fragment_coord_fab_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCoordList.setLayoutManager(linearLayoutManager);
        List<PeopleAdapterModel> peopleAdapterList = PeopleAdapterPerson.getPeopleAdapterList(People.getPeople());
        mCoordList.setAdapter(new PeopleAdapter(peopleAdapterList, this));
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.fragment_coord_fab_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        Toast.makeText(this, "WASSUP " + person.mName, Toast.LENGTH_SHORT).show();
    }
}
