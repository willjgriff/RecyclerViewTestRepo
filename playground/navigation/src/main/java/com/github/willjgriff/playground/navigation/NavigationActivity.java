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

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.RxJavaFun;
import com.github.willjgriff.playground.lists.ListsFragment;
import com.github.willjgriff.playground.network.api.TheMovieDb.TheMovieDbCalls;
import com.github.willjgriff.playground.network.model.movies.MoviesConfig;
import com.github.willjgriff.playground.utils.SharedPreferenceUtils;
import com.github.willjgriff.playground.utils.UiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
	// This ApiCall should probably live outside of the Activity. Ideally use MVP.
	private Call<MoviesConfig> mMoviesConfigCall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigation);

		mNavigationDrawer = (DrawerLayout) findViewById(R.id.activity_navigation_drawer);

		setupNavigationList();
		setupToolbar();
		setNavigationToggle();
		startupApiCalls();

		replaceFragment(new ListsFragment(), LISTS);

		getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
			@Override
			public void onBackStackChanged() {
				updateView();
			}
		});

		// This is just for testing RxAndroid, it will output using the standard Log.
		new RxJavaFun().play();
	}

	private void setupNavigationList() {
		mNavigationList = (RecyclerView) findViewById(R.id.activity_navigation_view);
		mNavigationList.setHasFixedSize(true);
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		mNavigationList.setLayoutManager(linearLayoutManager);
		mNavAdapter = new NavigationRecyclerViewAdapter(new NavigationEntries(this, this).getNavEntries());
		mNavigationList.setAdapter(mNavAdapter);
	}

	private void setupToolbar() {
		mToolbar = (Toolbar) findViewById(R.id.activity_navigation_toolbar);
		ViewCompat.setElevation(mToolbar, UiUtils.convertDpToPixel(4, this));
		setSupportActionBar(mToolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
		UiUtils.hideSoftKeyboard(getCurrentFocus(), this);
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
		mNavigationDrawer.addDrawerListener(mNavigationToggle);
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
		mMoviesConfigCall = TheMovieDbCalls.moviesConfigCall();
		mMoviesConfigCall.enqueue(new Callback<MoviesConfig>() {
			@Override
			public void onResponse(Call<MoviesConfig> call, Response<MoviesConfig> response) {
				Log.d("TAG", "Successfully retrieved TheMovieDb Configuration");
				SharedPreferenceUtils.writeObjectToPreferences(NavigationActivity.this, SHARED_MOVIES_CONFIG, response.body());
			}

			@Override
			public void onFailure(Call<MoviesConfig> call, Throwable t) {
				Log.e("TAG", "Failed to get TheMovieDb Configuration");
			}
		});
	}

	@Override
	protected void onStop() {
		if (mMoviesConfigCall != null) {
			mMoviesConfigCall.cancel();
			mMoviesConfigCall = null;
		}
		super.onStop();

	}
}
