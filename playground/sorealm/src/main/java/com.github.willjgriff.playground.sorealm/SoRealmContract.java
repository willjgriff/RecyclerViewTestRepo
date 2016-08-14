package com.github.willjgriff.playground.sorealm;

import com.github.willjgriff.playground.mvp.BasicMvpAlaGoogle.BasicPresenter;
import com.github.willjgriff.playground.mvp.BasicMvpAlaGoogle.BasicView;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestion;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Will on 10/08/2016.
 */

public interface SoRealmContract {

	interface View extends BasicView {

		void addAll(List<StackOverflowQuestion> soQuestions);

		void clearData();

		void showLoadingView();

		void showNetworkLoading();

		void hideLoadingViews();
	}

	interface Presenter extends BasicPresenter {

	}
}
