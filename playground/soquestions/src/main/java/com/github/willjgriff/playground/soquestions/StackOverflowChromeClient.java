package com.github.willjgriff.playground.soquestions;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Will on 08/06/2016.
 */
public class StackOverflowChromeClient extends WebChromeClient {

    private ProgressListener mProgressListener;

    public StackOverflowChromeClient(ProgressListener progressListener) {
        mProgressListener = progressListener;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        mProgressListener.loading(newProgress);
    }


    public interface ProgressListener {

        void loading(int progress);
    }
}
