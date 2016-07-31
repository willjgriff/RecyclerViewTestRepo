package com.github.willjgriff.playground.movies.Presenters;

import android.os.Build;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.github.willjgriff.playground.BuildConfig;
import com.github.willjgriff.playground.movies.MovieDetailsActivity;
import com.github.willjgriff.playground.RetrofitCallMock;
import com.github.willjgriff.playground.movies.Views.MovieDetailsView;
import com.github.willjgriff.playground.network.model.movies.MovieFull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import okhttp3.ResponseBody;
import retrofit2.Call;

import static retrofit2.Response.success;

/**
 * Created by Will on 17/05/2016.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class MovieDetailsPresenterTest {

	private MovieDetailsPresenter mPresenterSubject;

	private RetrofitCallMock<MovieFull> mMockRetrofitCall;
	@Mock
	private MovieDetailsView mMockActivity;
	@Mock
	private Call<MovieFull> mMockCall;

	@Before
	public void createActivityAndPresenter() {
		MockitoAnnotations.initMocks(this);
		mMockRetrofitCall = new RetrofitCallMock<>();
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsCalledWhenApiRequestSuccess() {
		mMockCall = mMockRetrofitCall.getSuccessfulRequest(Mockito.mock(MovieFull.class));
		mPresenterSubject = new MovieDetailsPresenterImpl(mMockCall);

		mPresenterSubject.bindView(mMockActivity);

		Mockito.verify(mMockActivity).setMovieName(Mockito.anyString());
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiRequestFails() {
		PowerMockito.mockStatic(Log.class);
		PowerMockito.when(Log.d(Mockito.anyString(), Mockito.anyString())).thenReturn(0);
		mMockCall = mMockRetrofitCall.getFailedRequest(new Throwable());
		mPresenterSubject = new MovieDetailsPresenterImpl(mMockCall);

		mPresenterSubject.bindView(mMockActivity);

		Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
	}

	@Test
	public void testMovieDetailsPresenter_UpdateViewIsNotCalledWhenApiResponseIsErroneous() {
		mMockCall = mMockRetrofitCall.getErroneousRequest(Mockito.mock(ResponseBody.class));
		mPresenterSubject = new MovieDetailsPresenterImpl(mMockCall);

		mPresenterSubject.bindView(mMockActivity);

		Mockito.verify(mMockActivity, Mockito.never()).setMovieName(Mockito.anyString());
	}
}
