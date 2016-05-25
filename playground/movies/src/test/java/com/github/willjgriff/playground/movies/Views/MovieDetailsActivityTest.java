package com.github.willjgriff.playground.movies.Views;

import android.content.Intent;
import android.os.Build;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import com.github.willjgriff.playground.BuildConfig;
import com.github.willjgriff.playground.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Will on 18/05/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@SmallTest
public class MovieDetailsActivityTest {

    private static final String EXTRA_TEST_MOVIE_ID = "elThoro";
    private MovieDetailsActivity mMovieDetailsActivity;

    @Before
    public void createActivity() {
        Intent intent = new Intent().putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, EXTRA_TEST_MOVIE_ID);
        mMovieDetailsActivity = Robolectric.buildActivity(MovieDetailsActivity.class).withIntent(intent).create().get();
    }

    @Test
    public void testMovieDetailsActivitySetName_setsTheCorrectName() {
        // Setup
        String expectedName = "que pasa";
        TextView movieName = (TextView) mMovieDetailsActivity.findViewById(R.id.activity_movie_details_title);

        // Act
        mMovieDetailsActivity.setMovieName(expectedName);

        // Assert
        Assert.assertNull(movieName);
        Assert.assertEquals(expectedName, movieName.getText().toString());
    }
}
