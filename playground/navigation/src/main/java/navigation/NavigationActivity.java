package navigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.StringRes;
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
import android.view.MenuItem;
import android.view.View;

import com.example.will.Playground.R;
import com.example.will.Playground.Utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import fab.FabFragment;
import lists.ListsFragment;
import tabs.TabLayoutFragment;
import web.WebViewFragment;

/**
 * Created by Will on 18/02/2016.
 */
public class NavigationActivity extends AppCompatActivity {

    private List<NavigationEntry> mNavEntries = new ArrayList<>();
    private ActionBarDrawerToggle mNavigationToggle;
    private RecyclerView mNavigationList;
    private DrawerLayout mNavigationDrawer;

    enum Tag {
        LISTS(R.string.action_list_activity),
        FAB(R.string.action_fab_fragment),
        TABS(R.string.action_tab_layout_fragment),
        WEB(R.string.action_web_view_fragment);

        int mTitle;

        Tag(@StringRes int title) {
            mTitle = title;
        }

        public int getTitle() {
            return mTitle;
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
        NavigationRecyclerViewAdapter adapter = new NavigationRecyclerViewAdapter(mNavEntries);
        mNavigationList.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_navigation_toolbar);
        ViewCompat.setElevation(toolbar, UiUtils.convertDpToPixel(4, this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_navigation_fragment_holder, new ListsFragment(), Tag.LISTS.name())
                .commit();

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

        if (!currentFragment.getTag().equals(tag.name())) {
            fragmentTransaction.addToBackStack(tag.name());
        }

        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
        // Unfortunate sometimes unnecessary command as tabLayout must remove the elevation.
        getSupportActionBar().setElevation(UiUtils.convertDpToPixel(4, this));

        mNavigationDrawer.closeDrawer(mNavigationList);
    }

    private void addNavEntries() {
        mNavEntries.add(new NavigationEntry(getString(Tag.LISTS.getTitle()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ListsFragment(), getString(Tag.LISTS.getTitle()), Tag.LISTS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.FAB.getTitle()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FabFragment(), getString(Tag.FAB.getTitle()), Tag.FAB);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.TABS.getTitle()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TabLayoutFragment(), getString(Tag.TABS.getTitle()), Tag.TABS);
            }
        }));
        mNavEntries.add(new NavigationEntry(getString(Tag.WEB.getTitle()), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new WebViewFragment(), getString(Tag.WEB.getTitle()), Tag.WEB);
            }
        }));
    }
}
