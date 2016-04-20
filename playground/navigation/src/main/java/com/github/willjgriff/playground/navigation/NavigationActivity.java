package com.github.willjgriff.playground.navigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.will.Playground.R;
import com.github.willjgriff.playground.network.TheMovieDb;
import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.lists.ListsFragment;
import com.github.willjgriff.playground.utils.SharedPreferenceUtils;
import com.github.willjgriff.playground.utils.UiUtils;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag;
import static com.github.willjgriff.playground.navigation.NavigationEntries.EntryTag.LISTS;
import static com.github.willjgriff.playground.utils.SharedPreferenceUtils.SHARED_MOVIES_CONFIG;

/**
 * Created by Will on 18/02/2016.
 * <p>
 * Note: In future I'll try using a {@link android.support.design.widget.NavigationView} instead of a
 * {@link RecyclerView} for navigation.
 */
public class NavigationActivity extends AppCompatActivity implements NavigationEntries.NavigationListener {

    private ActionBarDrawerToggle mNavigationToggle;
    private RecyclerView mNavigationList;
    private DrawerLayout mNavigationDrawer;
    private NavigationRecyclerViewAdapter mNavAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_view);
        mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);

        mNavigationList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mNavigationList.setLayoutManager(linearLayoutManager);
        mNavAdapter = new NavigationRecyclerViewAdapter(new NavigationEntries(this, this).getNavEntries());
        mNavigationList.setAdapter(mNavAdapter);

        mToolbar = (Toolbar) findViewById(R.id.activity_navigation_toolbar);
        ViewCompat.setElevation(mToolbar, UiUtils.convertDpToPixel(4, this));
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
        startupApiCalls();
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

    @Override
    public void onEntryClicked(Fragment fragment, NavigationEntries.EntryTag entryTag) {
        replaceFragment(fragment, entryTag);
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

    private void startupApiCalls() {
        TheMovieDb.moviesConfigCall().enqueue(new Callback<MoviesConfig>() {
            @Override
            public void onResponse(Response<MoviesConfig> response, Retrofit retrofit) {
                Log.d("TAG", "Successfully retrieved TheMovieDb Configuration");
                SharedPreferenceUtils.writeObjectToPreferences(NavigationActivity.this, SHARED_MOVIES_CONFIG, response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG", "Failed to get TheMovieDb Configuration");
            }
        });
    }
}
