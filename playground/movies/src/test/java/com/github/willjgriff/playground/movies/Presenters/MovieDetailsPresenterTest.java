package com.github.willjgriff.playground.movies.Presenters;

import android.os.Build;
import android.test.suitebuilder.annotation.SmallTest;

import com.github.willjgriff.playground.BuildConfig;
import com.github.willjgriff.playground.movies.MovieDetailsActivity;
import com.github.willjgriff.playground.network.model.movies.MovieFull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static retrofit.Response.success;

/**
 * Created by Will on 17/05/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@SmallTest
public class MovieDetailsPresenterTest {

    @Mock
    private MovieDetailsActivity mMockActivity;
    @Mock
    private Call<MovieFull> mMockCall;

    @Before
    public void createActivityAndPresenter() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMovieDetailsPresenter_UpdateViewIsCalledWhenApiRequestSuccess() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieFull> callback = (Callback<MovieFull>) invocation.getArguments()[0];
                callback.onResponse(success(new MovieFull()), null);
                return null;
            }
        }).when(mMockCall).enqueue(Mockito.any());

        MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);
        mSubjectPresenter.bindView(mMockActivity);
        Mockito.verify(mMockActivity).setMovieName(Mockito.anyString());
    }

    @Test
    public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiRequestFails() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieFull> callback = (Callback<MovieFull>) invocation.getArguments()[0];
                callback.onFailure(new Exception());
                return null;
            }
        }).when(mMockCall).enqueue(Mockito.any());

        MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);
        mSubjectPresenter.bindView(mMockActivity);
        Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
    }

    @Test
    public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiResopnseIsErroneous() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<MovieFull> callback = (Callback<MovieFull>) invocation.getArguments()[0];
                callback.onResponse(Response.error(404, null), null);
                return null;
            }
        }).when(mMockCall).enqueue(Mockito.any());

        MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);
        mSubjectPresenter.bindView(mMockActivity);
        Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
    }
}
