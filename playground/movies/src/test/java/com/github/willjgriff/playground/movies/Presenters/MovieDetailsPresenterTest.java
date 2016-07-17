package com.github.willjgriff.playground.movies.Presenters;

import android.os.Build;
import android.test.suitebuilder.annotation.SmallTest;

import com.github.willjgriff.playground.BuildConfig;
import com.github.willjgriff.playground.movies.MovieDetailsActivity;
import com.github.willjgriff.playground.RetrofitCallMock;
import com.github.willjgriff.playground.network.model.movies.MovieFull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import retrofit.Call;

import static retrofit.Response.success;

/**
 * Created by Will on 17/05/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@SmallTest
public class MovieDetailsPresenterTest {

	private RetrofitCallMock<MovieFull> mRetrofitCallMock;
	@Mock
	private MovieDetailsActivity mMockActivity;
	@Mock
	private Call<MovieFull> mMockCall;

	@Before
	public void createActivityAndPresenter() {
		MockitoAnnotations.initMocks(this);
		mRetrofitCallMock = new RetrofitCallMock<>();
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsCalledWhenApiRequestSuccess() {
		mMockCall = mRetrofitCallMock.getSuccessfulRequest(Mockito.mock(MovieFull.class));
		MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);

		mSubjectPresenter.bindView(mMockActivity);

		Mockito.verify(mMockActivity).setMovieName(Mockito.anyString());
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiRequestFails() {
		mMockCall = mRetrofitCallMock.getFailedRequest(new Throwable());
		MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);

		mSubjectPresenter.bindView(mMockActivity);

		Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiResponseIsErroneous() {
		mMockCall = mRetrofitCallMock.getErroneousRequest();
		MovieDetailsPresenter mSubjectPresenter = new MovieDetailsPresenterImpl("elMuavan", mMockCall);

		mSubjectPresenter.bindView(mMockActivity);

		Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
	}
}
