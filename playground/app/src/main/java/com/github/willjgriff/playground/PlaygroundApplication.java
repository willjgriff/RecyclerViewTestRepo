package com.github.willjgriff.playground;

import android.app.Application;

import com.github.willjgriff.playground.network.dagger2.components.DaggerSoComponent;
import com.github.willjgriff.playground.network.dagger2.components.SoComponent;
import com.github.willjgriff.playground.network.dagger2.modules.AppModule;
import com.github.willjgriff.playground.network.dagger2.modules.StackOverflowModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Will on 20/04/2016.
 */
public class PlaygroundApplication extends Application {

    private static PlaygroundApplication sApplication;
    private SoComponent mSoComponent;

    public static PlaygroundApplication app() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        mSoComponent = DaggerSoComponent.builder()
                .appModule(new AppModule(this))
                .stackOverflowModule(new StackOverflowModule())
                .build();

        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

    public SoComponent getSoComponent() {
        return mSoComponent;
    }
}
