package com.github.willjgriff.playground.network.dagger2.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Will on 11/05/2016.
 */
@Module
public class StorageModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
