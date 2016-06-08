package com.github.willjgriff.playground.soquestions;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Will on 08/06/2016.
 */
public class StackOverflowClient extends WebViewClient {

    private ProgressListener mProgressListener;

    public StackOverflowClient(ProgressListener progressListener) {
        mProgressListener = progressListener;
    }

//    @Override
//    public void onProgressChanged(WebView view, int newProgress) {
//        super.onProgressChanged(view, newProgress);
//    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mProgressListener.startLoading();
    }

    // TODO: This doesn't really work as the page doesn't stop loading for ages after it appears.
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        mProgressListener.stopLoading();
    }

    public interface ProgressListener {
        void startLoading();

        void stopLoading();
    }
}
