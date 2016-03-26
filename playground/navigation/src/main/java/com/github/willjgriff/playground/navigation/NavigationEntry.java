package com.github.willjgriff.playground.navigation;

import android.view.View;

/**
 * Created by Will on 22/02/2016.
 */
public class NavigationEntry {

    private String mNavigationTitle;
    private int mNavigationPosition;
    private View.OnClickListener mNavigationClickListener;

    public NavigationEntry(String navigationTitle, int navigationPosition, View.OnClickListener navigationClickListener) {
        this.mNavigationTitle = navigationTitle;
        this.mNavigationPosition = navigationPosition;
        this.mNavigationClickListener = navigationClickListener;
    }

    public String getNavigationTitle() {
        return mNavigationTitle;
    }

    public Integer getNavigationPosition() {
        return mNavigationPosition;
    }

    public View.OnClickListener getNavigationClickListener() {
        return mNavigationClickListener;
    }
}
