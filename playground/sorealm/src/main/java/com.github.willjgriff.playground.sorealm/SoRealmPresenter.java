package com.github.willjgriff.playground.sorealm;

import android.util.Log;

import com.github.willjgriff.playground.network.api.StackOverflow.StackOverflowApiCalls;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Will on 10/08/2016.
 */

public class SoRealmPresenter implements SoRealmContract.Presenter {

	private SoRealmContract.View mView;
	private Realm mRealm;
	private Call<StackOverflowQuestions> mStackOverflowQuestionsCall;
	private RealmChangeListener mRealmChangeListener;

	public SoRealmPresenter(SoRealmContract.View view) {
		mView = view;
		mRealm = Realm.getDefaultInstance();
	}

	@Override
	public void start() {

		RealmResults<StackOverflowQuestion> realmResults = mRealm.where(StackOverflowQuestion.class).findAll();
		mView.addAll(realmResults);

		mRealmChangeListener = new RealmChangeListener() {
			@Override
			public void onChange(Object element) {
				mView.clearAdapter();
			}
		};

		mRealm.addChangeListener(mRealmChangeListener);

		getAndUpdateSoQuestions();
	}

	private void getAndUpdateSoQuestions() {
		mStackOverflowQuestionsCall = StackOverflowApiCalls.androidQuestionsCall();
		mStackOverflowQuestionsCall.enqueue(new Callback<StackOverflowQuestions>() {
			@Override
			public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
				StackOverflowQuestions soQuestions = response.body();
				updateRealmWithSoQuestions(soQuestions);
			}

			@Override
			public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {

			}
		});
	}

	private void updateRealmWithSoQuestions(final StackOverflowQuestions soQuestions) {
		mRealm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				realm.copyToRealmOrUpdate(soQuestions.getQuestions());
			}
		});
	}

	@Override
	public void stop() {
		mRealm.removeChangeListener(mRealmChangeListener);
		mRealm.close();
		mStackOverflowQuestionsCall.cancel();
	}
}
