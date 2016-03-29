package com.github.willjgriff.playground.navigation;

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
import com.github.willjgriff.playground.UiUtils;
import com.github.willjgriff.playground.coord.CoordsFragment;
import com.github.willjgriff.playground.lists.ListsFragment;
import com.github.willjgriff.playground.movies.TopMoviesFragment;
import com.github.willjgriff.playground.tabs.TabLayoutFragment;
import com.github.willjgriff.playground.soquestions.StackOverflowQuestionsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.willjgriff.playground.navigation.NavigationActivity.EntryTag.COORD;
import static com.github.willjgriff.playground.navigation.NavigationActivity.EntryTag.LISTS;
import static com.github.willjgriff.playground.navigation.NavigationActivity.EntryTag.TABS;

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
    private Toolbar mToolbar;

    enum EntryTag {
        LISTS(R.string.navigation_list_activity, R.integer.navigation_list_activity),
        COORD(R.string.navigation_coord_fragment, R.integer.navigation_coord_fragment),
        TABS(R.string.navigation_tab_layout_fragment, R.integer.navigation_tab_layout_fragment),
        SO_QUESTIONS(R.string.navigation_so_questions_fragment, R.integer.navigation_so_questions_fragment),
        TOP_MOVIES(R.string.navigation_top_movies_fragment, R.integer.navigation_top_movies_fragment);

        private int mTitle;
        private int mPosition;

        EntryTag(@StringRes int title, int position) {
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

        mToolbar = (Toolbar) findViewById(R.id.activity_navigation_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        replaceFragment(new ListsFragment(), LISTS);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                updateView();
            }
        });

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
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item) || mNavigationToggle.onOptionsItemSelected(item);
    }

    private void updateView() {
        String fragmentTag = getSupportFragmentManager().findFragmentById(R.id.activity_navigation_fragment_holder).getTag();
        EntryTag entryTag = EntryTag.valueOf(fragmentTag);

        // Update selected navigation entry
        int entryPositionRes = entryTag.getPosition();
        mNavAdapter.setSelectedPosition(getResources().getInteger(entryPositionRes) - 1);

        // Update toolbar title
        int entryTitleRes = entryTag.getTitle();
        getSupportActionBar().setTitle(getString(entryTitleRes));

        mNavigationDrawer.closeDrawer(mNavigationList);
    }

    private void setNavigationToggle() {
        mNavigationToggle = new ActionBarDrawerToggle(this, mNavigationDrawer, R.string.drawer_open, R.string.drawer_close);
        mNavigationToggle.setDrawerIndicatorEnabled(true);
        mNavigationDrawer.setDrawerListener(mNavigationToggle);
    }

    private void replaceFragment(Fragment fragment, EntryTag entryTag) {
        if (getSupportActionBar().getElevation() == 0) {
            // The tabLayout must remove the elevation so we put it back here.
            // TODO: This doesn't work. Elevation can be left at 0 when popping backstack. Needs rethinking.
            getSupportActionBar().setElevation(UiUtils.convertDpToPixel(4, NavigationActivity.this));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.activity_navigation_fragment_holder);

        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out, R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                .replace(R.id.activity_navigation_fragment_holder, fragment, entryTag.name());

        if (currentFragment != null && currentFragment.getTag().equals(entryTag.name())) {
            mNavigationDrawer.closeDrawer(mNavigationList);
        } else {
            fragmentTransaction.addToBackStack(entryTag.name());
        }

        fragmentTransaction.commit();
    }

    private void addNavEntries() {
        mNavEntries.add(new NavigationEntry(getString(LISTS.getTitle()), getResources().getInteger(LISTS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ListsFragment(), LISTS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(COORD.getTitle()), getResources().getInteger(COORD.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CoordsFragment(), COORD);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(TABS.getTitle()), getResources().getInteger(TABS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TabLayoutFragment(), TABS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(EntryTag.SO_QUESTIONS.getTitle()), getResources().getInteger(EntryTag.SO_QUESTIONS.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new StackOverflowQuestionsFragment(), EntryTag.SO_QUESTIONS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(EntryTag.TOP_MOVIES.getTitle()), getResources().getInteger(EntryTag.TOP_MOVIES.getPosition()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TopMoviesFragment(), EntryTag.TOP_MOVIES);
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
