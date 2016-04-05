package com.github.willjgriff.playground.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Will on 04/04/2016.
 */
public abstract class MvpPresenter<VIEW> {

    private VIEW mView;

    public void setView(VIEW view) {
        mView = view;
    }

    public VIEW getView() {
        return mView;
    }

    protected void onCreate(@Nullable Bundle savedState) {
    }

    protected void onResume() {
        // Generally start Api requests here
    }

    protected void onPause() {
        // Generally stop Api requests here
    }
//
//    protected void onSave(Bundle state) {
//    }
//
//    private List<OnDestroyListener> onDestroyListeners = new ArrayList<>();
//
//    public interface OnDestroyListener {
//        void onDestroy();
//    }
//
//    public void addOnDestroyListener(OnDestroyListener onDestroyListener) {
//        onDestroyListeners.add(onDestroyListener);
//    }
//
//    public void removeOnDestroyListener(OnDestroyListener onDestroyListener) {
//        onDestroyListeners.remove(onDestroyListener);
//    }
//
//    protected void destroy() {
//        for (OnDestroyListener onDestroyListener : onDestroyListeners) {
//            onDestroyListener.onDestroy();
//        }
//        onDestroy();
//    }
}
