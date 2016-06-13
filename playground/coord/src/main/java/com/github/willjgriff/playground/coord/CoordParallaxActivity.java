package com.github.willjgriff.playground.coord;

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
import android.widget.Toast;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.lists.adapters.PeopleAdapter;
import com.github.willjgriff.playground.lists.adapters.viewholders.PersonViewHolder.PersonItemListener;
import com.github.willjgriff.playground.lists.model.data.People;
import com.github.willjgriff.playground.lists.model.PeopleAdapterModel;
import com.github.willjgriff.playground.lists.model.PeopleAdapterPerson;
import com.github.willjgriff.playground.lists.model.Person;

import java.util.List;

/**
 * Created by Will on 13/03/2016.
 */
public class CoordParallaxActivity extends AppCompatActivity implements PersonItemListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_coord_parallax_toolbar);

        setupPeopleList();
        setupToolbar();

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

    private void setupPeopleList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fragment_coord_toolbar_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<PeopleAdapterModel> peopleAdapterList = PeopleAdapterPerson.getPeopleAdapterList(People.getPeople());
        PeopleAdapter adapter = new PeopleAdapter(peopleAdapterList, this);
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.fragment_coord_toolbar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void personItemClick(Person person, View transitionImage, View transitionName, View transitionAge) {
        Toast.makeText(this, "WASSUP " + person.mName, Toast.LENGTH_SHORT).show();
    }
}
