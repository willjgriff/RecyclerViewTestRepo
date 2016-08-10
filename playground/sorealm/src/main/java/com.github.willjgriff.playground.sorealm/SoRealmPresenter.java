package com.github.willjgriff.playground.sorealm;

import com.github.willjgriff.playground.network.api.StackOverflow.StackOverflowApiCalls;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import io.realm.Realm;
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

	public SoRealmPresenter(SoRealmContract.View view) {
		mView = view;
		mRealm = Realm.getDefaultInstance();
	}

	@Override
	public void start() {
		mStackOverflowQuestionsCall = StackOverflowApiCalls.androidQuestionsCall();
		mStackOverflowQuestionsCall.enqueue(new Callback<StackOverflowQuestions>() {
			@Override
			public void onResponse(Call<StackOverflowQuestions> call, Response<StackOverflowQuestions> response) {
				StackOverflowQuestions soQuestions = response.body();

				updateRealm(soQuestions);
			}

			@Override
			public void onFailure(Call<StackOverflowQuestions> call, Throwable t) {

			}
		});
	}

	private void updateRealm(final StackOverflowQuestions soQuestions) {
		mRealm.executeTransaction(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				realm.copyToRealmOrUpdate(soQuestions.getQuestions());

				RealmResults<StackOverflowQuestion> realmResults = realm.where(StackOverflowQuestion.class).findAll();

				mView.addAll(realmResults);
			}
		});
	}

	@Override
	public void stop() {
		mRealm.close();
		mStackOverflowQuestionsCall.cancel();
	}
}
