package com.github.willjgriff.playground.sorealm.data.realm;

import com.github.willjgriff.playground.network.model.stackoverflow.StackOverflowQuestions;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmModel;

/**
 * Created by Will on 14/08/2016.
 *
 * Encapsulates basic realm transaction functionality.
 * Abstracting this behaviour might be overkill. We'll see when we come to use it.
 */
public abstract class RealmUpdater<UPDATETYPE extends RealmModel> {

	private Realm mRealm;
	private RealmAsyncTask mRealmAsyncTask;

	public RealmUpdater(Realm realm) {
		mRealm = realm;
	}

	public void update(UPDATETYPE updatedData) {
		mRealmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
			@Override
			public void execute(Realm realm) {
				updateRealm(realm, updatedData);
			}
		});
	}

	public void close() {
		if (mRealmAsyncTask != null) {
			mRealmAsyncTask.cancel();
			mRealmAsyncTask = null;
		}
	}

	public abstract void updateRealm(Realm realm, UPDATETYPE updatedData);

}
