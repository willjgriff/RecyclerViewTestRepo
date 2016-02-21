package com.example.will.Playground;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 18/02/2016.
 */
public class NavigationActivity extends AppCompatActivity {

    private RecyclerView mNavigationList;
    private ActionBarDrawerToggle mNavigationToggle;
    private DrawerLayout mNavigationDrawer;
    private String mActivityTitle;
    private static List<String> navEntries = new ArrayList<>();

    {
        navEntries.add("Room satu");
        navEntries.add("Roomo dua");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_list);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);
        mActivityTitle = getTitle().toString();

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getNavigationItems());
//        mNavigationList.setAdapter(arrayAdapter);

        mNavigationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNavigationList.setLayoutManager(linearLayoutManager);
        NavigationRecyclerViewAdapter adapter = new NavigationRecyclerViewAdapter(navEntries);
//        PeopleRecyclerViewAdapter adapter = new PeopleRecyclerViewAdapter(this, People.getPeople(), R.layout.fragment_recycler_view_item);
        mNavigationList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setNavigationToggle();

    }

    private void setNavigationToggle() {
        mNavigationToggle = new ActionBarDrawerToggle(this, mNavigationDrawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // We can do stuff here
//                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // We can do stuff here
//                invalidateOptionsMenu();
            }
        };
        mNavigationToggle.setDrawerIndicatorEnabled(true);
        mNavigationDrawer.setDrawerListener(mNavigationToggle);
    }

    private List<String> getNavigationItems() {
        List<String> navigationItems = new ArrayList<String>();
        navigationItems.add("Satu");
        navigationItems.add("Dua");
        navigationItems.add("Tiga");
        return navigationItems;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item) || mNavigationToggle.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationToggle.syncState();
    }
}
