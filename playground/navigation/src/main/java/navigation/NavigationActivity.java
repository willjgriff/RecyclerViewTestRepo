package navigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.will.Playground.R;
import com.example.will.Playground.Utils.UiUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coord.CoordFragment;
import lists.ListsFragment;
import tabs.TabLayoutFragment;
import web.WebViewFragment;

/**
 * Created by Will on 18/02/2016.
 * </p>
 * Note: In future I'll use a NavigationView instead of a RecyclerView
 * for navigation.
 */
public class NavigationActivity extends AppCompatActivity {

    private List<NavigationEntry> mNavEntries = new ArrayList<>();
    private ActionBarDrawerToggle mNavigationToggle;
    private RecyclerView mNavigationList;
    private DrawerLayout mNavigationDrawer;
    private NavigationRecyclerViewAdapter mNavAdapter;

    enum Tag {
        LISTS(R.string.navigation_list_activity, R.integer.navigation_list_activity),
        COORD(R.string.navigation_coord_fragment, R.integer.navigation_coord_fragment),
        TABS(R.string.navigation_tab_layout_fragment, R.integer.navigation_tab_layout_fragment),
        WEB(R.string.navigation_web_view_fragment, R.integer.navigation_web_view_fragment);

        private int mTitle;
        private int mPosition;

        Tag(@StringRes int title, int position) {
            mTitle = title;
            mPosition = position;
        }

        public int getTitle() {
            return mTitle;
        }

        public int getPosition() {
            return mPosition;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_view);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);

        addNavEntries();

        mNavigationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNavigationList.setLayoutManager(linearLayoutManager);
        mNavAdapter = new NavigationRecyclerViewAdapter(mNavEntries);
        mNavigationList.setAdapter(mNavAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_navigation_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        replaceFragment(new ListsFragment(), getString(Tag.LISTS.getTitle()), Tag.LISTS);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item) || mNavigationToggle.onOptionsItemSelected(item);
    }

    private void setNavigationToggle() {
        mNavigationToggle = new ActionBarDrawerToggle(this, mNavigationDrawer, R.string.drawer_open, R.string.drawer_close);
        mNavigationToggle.setDrawerIndicatorEnabled(true);
        mNavigationDrawer.setDrawerListener(mNavigationToggle);
    }

    private void replaceFragment(Fragment fragment, String title, Tag tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.activity_navigation_fragment_holder);

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out, R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                .replace(R.id.activity_navigation_fragment_holder, fragment, tag.name());

        if (currentFragment != null && !currentFragment.getTag().equals(tag.name())) {
            fragmentTransaction.addToBackStack(tag.name());
        }

        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
        // Unfortunate often unnecessary command as tabLayout must remove the elevation.
        getSupportActionBar().setElevation(UiUtils.convertDpToPixel(4, this));

        mNavigationDrawer.closeDrawer(mNavigationList);
        mNavAdapter.setSelectedPosition(getResources().getInteger(tag.getPosition()) - 1);
    }

    private void addNavEntries() {
        mNavEntries.add(new NavigationEntry(getString(Tag.LISTS.getTitle()), getResources().getInteger(Tag.LISTS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ListsFragment(), getString(Tag.LISTS.getTitle()), Tag.LISTS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.COORD.getTitle()), getResources().getInteger(Tag.COORD.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CoordFragment(), getString(Tag.COORD.getTitle()), Tag.COORD);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.TABS.getTitle()), getResources().getInteger(Tag.TABS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TabLayoutFragment(), getString(Tag.TABS.getTitle()), Tag.TABS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.WEB.getTitle()), getResources().getInteger(Tag.WEB.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new WebViewFragment(), getString(Tag.WEB.getTitle()), Tag.WEB);
            }
        }));

        Collections.sort(mNavEntries, new Comparator<NavigationEntry>() {
            @Override
            public int compare(NavigationEntry lhs, NavigationEntry rhs) {
                return lhs.getNavigationPosition().compareTo(rhs.getNavigationPosition());
            }
        });
    }
}
