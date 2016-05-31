package com.github.willjgriff.playground.movies;

import android.content.Intent;
import android.os.Build;
import android.test.suitebuilder.annotation.SmallTest;

import com.github.willjgriff.playground.BuildConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

/**
 * Created by Will on 28/05/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@SmallTest
public class TopMoviesFragmentTest {

    private static final String EXPECTED_MOVIE_ID = "el muava";

    TopMoviesFragment mFragmentSubject;

    @Before
    public void setupTopMoviesFragment() {
        mFragmentSubject = new TopMoviesFragment();
        SupportFragmentTestUtil.startFragment(mFragmentSubject);
    }

    @Test
    public void testOpenMovieDetailsScreen_opensMovieDetailsActivity() {
        mFragmentSubject = Mockito.spy(mFragmentSubject);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Intent intent = (Intent) invocation.getArguments()[0];
                Assert.assertEquals(MovieDetailsActivity.class.getName(), intent.getComponent().getClassName());
                return null;
            }
        }).when(mFragmentSubject).startActivity(Mockito.any());

        mFragmentSubject.openMovieDetailScreen(EXPECTED_MOVIE_ID);
    }

    @Test
    public void testOpenMovieDetailsScreen_setsTheCorrectIntent() {
        mFragmentSubject = Mockito.spy(mFragmentSubject);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Intent intent = (Intent) invocation.getArguments()[0];
                Assert.assertTrue(intent.hasExtra(MovieDetailsActivity.EXTRA_MOVIE_ID));
                Assert.assertEquals(EXPECTED_MOVIE_ID, intent.getStringExtra(MovieDetailsActivity.EXTRA_MOVIE_ID));
                return null;
            }
        }).when(mFragmentSubject).startActivity(Mockito.any());

        mFragmentSubject.openMovieDetailScreen(EXPECTED_MOVIE_ID);
    }

}
