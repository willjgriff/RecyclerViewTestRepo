package navigation;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.will.Playground.OtherFragment;
import com.example.will.Playground.R;

import java.util.ArrayList;
import java.util.List;

import lists.ListsFragment;
import tabs.TabLayoutFragment;

/**
 * Created by Will on 18/02/2016.
 */
public class NavigationActivity extends AppCompatActivity {

    private List<NavigationEntry> mNavEntries = new ArrayList<>();
    private ActionBarDrawerToggle mNavigationToggle;
    private RecyclerView mNavigationList;
    private DrawerLayout mNavigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_list);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);

        addNavEntries();

        mNavigationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNavigationList.setLayoutManager(linearLayoutManager);
        NavigationRecyclerViewAdapter adapter = new NavigationRecyclerViewAdapter(mNavEntries);
        mNavigationList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setNavigationToggle();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNavigationToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_activity:
                replaceFragment(new ListsFragment(), getString(R.string.action_list_activity));
                break;
            case R.id.action_navigation_activity:
                startActivity(new Intent(this, NavigationActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item) || mNavigationToggle.onOptionsItemSelected(item);
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

    private void replaceFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_navigation_fragment_holder, fragment)
                .commit();
        getSupportActionBar().setTitle(title);
        mNavigationDrawer.closeDrawer(mNavigationList);
    }

    private void addNavEntries() {
        mNavEntries.add(new NavigationEntry(getString(R.string.action_list_activity), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ListsFragment(), getString(R.string.action_list_activity));
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(R.string.action_other_fragment), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new OtherFragment(), getString(R.string.action_other_fragment));
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(R.string.action_tab_layout_fragment), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TabLayoutFragment(), getString(R.string.action_tab_layout_fragment));
            }
        }));
    }
}
