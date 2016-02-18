package com.example.will.Playground;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 18/02/2016.
 */
public class NavigationActivity extends AppCompatActivity {

    private ListView mNavigationList;
    private DrawerLayout mNavigationDrawer;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationList = (ListView) findViewById(R.id.activity_navigation_list);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);
        mActivityTitle = getTitle().toString();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getNavigationItems());
        mNavigationList.setAdapter(arrayAdapter);
    }

    private List<String> getNavigationItems() {
        List<String> navigationItems = new ArrayList<String>();
        navigationItems.add("Satu");
        navigationItems.add("Dua");
        navigationItems.add("Tiga");
        return navigationItems;
    }
}
