package com.github.willjgriff.playground.movies;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.navigation.NavigationActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Will on 19/07/2016.
 * <p>
 * Note that we should change the build variant / app flavor to mock to use a different TopMoviesData
 * file (which should be generalised to a repository). Use 'mockDebug' or 'mockRelease'
 * Alternatively we could use a mock server and mock retrofit calls.
 */
@RunWith(AndroidJUnit4.class)
public class TopMoviesFragmentAndroidTest {

	@Rule
	public ActivityTestRule<NavigationActivity> mNavigationActivityRule = new ActivityTestRule<>(NavigationActivity.class);

	@Before
	public void openTopMoviesFragment() {
		Espresso.onView(ViewMatchers.withId(R.id.activity_navigation_drawer)).perform(DrawerActions.open());
		Espresso.onView(Matchers.allOf(ViewMatchers.withText("Top Movies"))).perform(ViewActions.click());
	}

	@Test
	public void topMoviesFirstItem_displaysTextFromTheFirstListItem() {
		String expectedListItemTitle = "The Legend of Tarzan";
		Espresso.onView(ViewMatchers.withText(expectedListItemTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}
}
