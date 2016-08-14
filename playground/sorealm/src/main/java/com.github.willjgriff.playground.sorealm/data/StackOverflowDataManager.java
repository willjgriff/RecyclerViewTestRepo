package com.github.willjgriff.playground.sorealm.data;

import com.github.willjgriff.playground.network.api.StackOverflow.StackOverflowApiCalls;
import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;
import com.github.willjgriff.playground.sorealm.data.NetworkCallerAndUpdater.NewDataListener;
import com.github.willjgriff.playground.sorealm.data.realm.AllRealmFetcher;
import com.github.willjgriff.playground.sorealm.data.realm.RealmFetcher;
import com.github.willjgriff.playground.sorealm.data.realm.RealmUpdater;
import com.github.willjgriff.playground.sorealm.data.realm.ReplaceRealmUpdater;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Will on 13/08/2016.
 *
 * A data manager that constructs cached data requests using the Realm database.
 * Functions will return current data available from the Realm and accept a listener
 * that will be called when new results have been successfully fetched.
 */
public class StackOverflowDataManager {

	private Realm mRealm;
	private NetworkCallerAndUpdater<StackOverflowQuestions> mSoQuestionsNetworkCallerAndUpdater;

	public StackOverflowDataManager() {
		// Inject the Realm using Dagger2. Figure out how to close it safely.
		mRealm = Realm.getDefaultInstance();
	}

	/**
	 * Returns the currently stored data, if it is present, null otherwise, and sets
	 * a listener that is called when new data from the network is retrieved.
	 *
	 * @param soQuestionsListener called when new data is retrieved
	 * @return currently saved data if present, null otherwise
	 */
	public StackOverflowQuestions getStackOverflowQuestions(NewDataListener<StackOverflowQuestions> soQuestionsListener) {
		RealmFetcher<StackOverflowQuestions> realmFetcher = new AllRealmFetcher<>(StackOverflowQuestions.class);
		RealmUpdater<StackOverflowQuestions> realmUpdater = new ReplaceRealmUpdater<>(mRealm, realmFetcher);

		mSoQuestionsNetworkCallerAndUpdater = new NetworkCallerAndUpdater<>(
			StackOverflowApiCalls.androidQuestionsCall(), soQuestionsListener, realmUpdater);
		mSoQuestionsNetworkCallerAndUpdater.fetchAndUpdateData();

		// TODO: We should use the same realm fetch method when updating the realm as when we display realm
		// data to the user. Try to find a way of forcing the use of the same realmFetcher for both purposes
		RealmResults<StackOverflowQuestions> savedSoQuestions = realmFetcher.fetch(mRealm);
		if (savedSoQuestions.size() >= 1) {
			return savedSoQuestions.get(0);
		} else {
			return null;
		}
	}

	public void close() {
		if (mSoQuestionsNetworkCallerAndUpdater != null) {
			mSoQuestionsNetworkCallerAndUpdater.close();
			mSoQuestionsNetworkCallerAndUpdater = null;
		}
		if (mRealm != null) {
			mRealm.close();
			mRealm = null;
		}
	}
}
