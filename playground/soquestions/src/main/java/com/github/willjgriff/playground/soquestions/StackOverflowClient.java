package com.github.willjgriff.playground.soquestions;

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

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

//    @Override
//    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        super.onPageStarted(view, url, favicon);
//        mProgressListener.startLoading();
//    }
//
//    @Override
//    public void onPageFinished(WebView view, String url) {
//        super.onPageFinished(view, url);
//        mProgressListener.stopLoading();
//    }

    public interface ProgressListener {
        void startLoading();

        void stopLoading();
    }
}
