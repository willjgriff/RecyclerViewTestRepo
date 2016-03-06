package navigation;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.will.Playground.ListsActivity;
import com.example.will.Playground.OtherActivity;
import com.example.will.Playground.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 22/02/2016.
 */
public class NavigationDrawerSetup {

    private DrawerLayout mNavigationDrawer;
    private RecyclerView mNavItemList;
    private ActionBar mSupportActionBar;
    private AppCompatActivity mNavigationActivity;
    private ActionBarDrawerToggle mNavigationToggle;
    private List<NavigationEntry> mNavEntries = new ArrayList<>();

    public NavigationDrawerSetup(DrawerLayout mNavigationDrawer, RecyclerView mNavItemList, ActionBar mSupportActionBar, AppCompatActivity mNavigationActivity) {
        this.mNavigationDrawer = mNavigationDrawer;
        this.mNavItemList = mNavItemList;
        this.mSupportActionBar = mSupportActionBar;
        this.mNavigationActivity = mNavigationActivity;
    }

    public ActionBarDrawerToggle setupNavigationDrawer() {
        addNavEntries();

        mNavItemList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mNavigationActivity);
        mNavItemList.setLayoutManager(linearLayoutManager);
        NavigationRecyclerViewAdapter adapter = new NavigationRecyclerViewAdapter(mNavEntries);
        mNavItemList.setAdapter(adapter);

        mSupportActionBar.setDisplayHomeAsUpEnabled(true);
        mSupportActionBar.setHomeButtonEnabled(true);

        setNavigationToggle();

        return mNavigationToggle;
    }

    private void setNavigationToggle() {
        mNavigationToggle = new ActionBarDrawerToggle(mNavigationActivity, mNavigationDrawer, R.string.drawer_open, R.string.drawer_close) {
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
        FragmentManager fragmentManager = mNavigationActivity.getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_navigation_fragment_holder, fragment)
                .commit();
        mSupportActionBar.setTitle(title);
        mNavigationDrawer.closeDrawer(mNavItemList);
    }

    private void addNavEntries() {
        mNavEntries.add(new NavigationEntry(mNavigationActivity.getString(R.string.action_list_activity), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mNavigationActivity, ListsActivity.class);
                mNavigationActivity.startActivity(intent);
            }
        }));
        mNavEntries.add(new NavigationEntry(mNavigationActivity.getString(R.string.action_other_activity), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mNavigationActivity, OtherActivity.class);
                mNavigationActivity.startActivity(intent);
            }
        }));
        mNavEntries.add(new NavigationEntry(mNavigationActivity.getString(R.string.action_tab_layout_fragment), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TabLayoutFragment(), mNavigationActivity.getString(R.string.action_tab_layout_fragment));
            }
        }));
    }
}
