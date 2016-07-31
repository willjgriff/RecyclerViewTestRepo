package com.github.willjgriff.playground;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static retrofit2.Response.success;

/**
 * Created by Will on 17/07/2016.
 */
public class RetrofitCallMock<RETURNTYPE> {

	private Call<RETURNTYPE> mMockCall;

	public RetrofitCallMock() {
		mMockCall = Mockito.mock(Call.class);
	}

	public Call<RETURNTYPE> getSuccessfulRequest(RETURNTYPE returnObject) {
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Callback<RETURNTYPE> callback = (Callback<RETURNTYPE>) invocation.getArguments()[0];
				callback.onResponse(mMockCall, success(returnObject));
				return null;
			}
		}).when(mMockCall).enqueue(Mockito.any());

		return mMockCall;
	}

	public Call<RETURNTYPE> getFailedRequest(Throwable throwable) {
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Callback<RETURNTYPE> callback = (Callback<RETURNTYPE>) invocation.getArguments()[0];
				callback.onFailure(mMockCall, throwable);
				return null;
			}
		}).when(mMockCall).enqueue(Mockito.any());

		return mMockCall;
	}

	public Call<RETURNTYPE> getErroneousRequest(ResponseBody mockResponseBody) {
		Mockito.doAnswer(new Answer() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Callback<RETURNTYPE> callback = (Callback<RETURNTYPE>) invocation.getArguments()[0];
				callback.onResponse(mMockCall, Response.error(404, mockResponseBody));
				return null;
			}
		}).when(mMockCall).enqueue(Mockito.any());

		return mMockCall;
	}
}
