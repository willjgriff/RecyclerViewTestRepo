package com.github.willjgriff.playground.sorealm;

import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;
import com.github.willjgriff.playground.sorealm.data.NetworkCallerAndUpdater.NewDataListener;
import com.github.willjgriff.playground.sorealm.data.StackOverflowDataManager;

/**
 * Created by Will on 10/08/2016.
 */
public class SoRealmPresenter implements SoRealmContract.Presenter {

	private SoRealmContract.View mView;
	private StackOverflowDataManager mStackOverflowDataManager;

	public SoRealmPresenter(SoRealmContract.View view, StackOverflowDataManager soDataManager) {
		mView = view;
		mStackOverflowDataManager = soDataManager;
	}

	@Override
	public void start() {
		StackOverflowQuestions savedSoQuestions = mStackOverflowDataManager.getStackOverflowQuestions(new NewDataListener<StackOverflowQuestions>() {
			@Override
			public void dataUpdated(StackOverflowQuestions stackOverflowQuestions) {
				// Ideally animate in the new list items
				mView.clearData();
				mView.addAll(stackOverflowQuestions.getStackOverflowQuestions());
				mView.hideNetworkLoading();
			}

			@Override
			public void requestFailed(Throwable t) {

			}
		});

		// Check if we have cached data in the DB and show appropriate loading states.
		if (savedSoQuestions != null && savedSoQuestions.getStackOverflowQuestions() != null) {
			mView.addAll(savedSoQuestions.getStackOverflowQuestions());
			mView.showNetworkLoading();
		} else {
			mView.showLoadingView();
		}
	}

	@Override
	public void stop() {
		mStackOverflowDataManager.close();
	}
}
