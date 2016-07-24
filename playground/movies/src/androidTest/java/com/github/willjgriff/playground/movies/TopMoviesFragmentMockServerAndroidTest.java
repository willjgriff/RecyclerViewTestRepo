package com.github.willjgriff.playground.movies;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.github.willjgriff.playground.R;
import com.github.willjgriff.playground.navigation.NavigationActivity;
import com.github.willjgriff.playground.network.api.ApiUris;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Will on 19/07/2016.
 * <p>
 * Note that we could change the build variant instead of mocking the server like this.
 * Here though we mock the server. Execute these tests with build variant 'prodDebug' or 'prodRelease'.
 */
@RunWith(AndroidJUnit4.class)
public class TopMoviesFragmentMockServerAndroidTest extends InstrumentationTestCase {

	@Rule
	public ActivityTestRule<NavigationActivity> mNavigationActivityRule = new ActivityTestRule<>(NavigationActivity.class, true, false);
	private String contentType = "Content-type: application/json";
	private MockWebServer mMockServer;

	@Before
	public void openTopMoviesFragment() throws Exception {
		// Try alternative ways of getting the context.
		injectInstrumentation(InstrumentationRegistry.getInstrumentation());

		setupMockServer();

		mNavigationActivityRule.launchActivity(new Intent());
		Espresso.onView(ViewMatchers.withId(R.id.activity_navigation_drawer)).perform(DrawerActions.open());
	}

	private void setupMockServer() throws Exception {
		mMockServer = new MockWebServer();
		mMockServer.start();
		ApiUris.TheMovieDb.URI_THE_MOVIE_DB = mMockServer.url("/").toString();
		addConfigResponseToServer(mMockServer);
	}

	// TheMovieDb config request is made when we open the NavActivity, so this must be enqueued first.
	private void addConfigResponseToServer(MockWebServer mockServer) throws Exception {
		String movieConfigJson = TopMoviesTestHelper.getStringFromFile(getInstrumentation().getContext(), "TheMovieDbConfig.json");
		mockServer.enqueue(new MockResponse().setResponseCode(200).setBody(movieConfigJson).addHeader(contentType));
	}

	@Test
	public void topMoviesFirstItem_displaysTextFromTheFirstListItem() throws Exception {
		addMovieListResponseToServer(mMockServer);

		Espresso.onView(Matchers.allOf(ViewMatchers.withText("Top Movies"))).perform(ViewActions.click());
		String expectedListItemTitle = "The Legend of Tarzan";
		Espresso.onView(ViewMatchers.withText(expectedListItemTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
	}

	private void addMovieListResponseToServer(MockWebServer mockServer) throws Exception {
		String movieListJson = TopMoviesTestHelper.getStringFromFile(getInstrumentation().getContext(), "TheMovieDbListJson.json");
		mockServer.enqueue(new MockResponse().setResponseCode(200).setBody(movieListJson).addHeader(contentType));
	}
}
