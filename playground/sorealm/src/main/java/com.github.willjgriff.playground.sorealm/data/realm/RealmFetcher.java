package com.github.willjgriff.playground.sorealm.data.realm;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Will on 14/08/2016.
 */

public interface RealmFetcher<RETURNTYPE extends RealmModel> {

	RealmResults<RETURNTYPE> fetch(Realm realm);
}
