package com.github.willjgriff.playground;

import android.app.Application;

/**
 * Created by Will on 20/04/2016.
 */
public class PlaygroundApplication extends Application {

    private static PlaygroundApplication sApplication;

    public static PlaygroundApplication app() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
