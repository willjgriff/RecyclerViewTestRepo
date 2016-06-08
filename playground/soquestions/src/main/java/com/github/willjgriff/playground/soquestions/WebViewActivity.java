package com.github.willjgriff.playground.soquestions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.github.willjgriff.playground.R;

/**
 * Created by Will on 08/06/2016.
 */

public class WebViewActivity extends AppCompatActivity implements StackOverflowClient.ProgressListener {

    private static final String ARG_INITIAL_LINK = "com.github.willjgriff.plaground.soquestions.WebViewActivity;ARG_INITIAL_LINK";
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static void startInstance(Context context, String initialLink) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ARG_INITIAL_LINK, initialLink);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.activity_web_view_web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.activity_web_view_progress);

        String initialLink = getIntent().getExtras().getString(ARG_INITIAL_LINK);
        loadLink(initialLink);
    }

    private void setupWebView() {
        // Configure browser settings
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Configure the client to use when opening URLs
        mWebView.setWebViewClient(new StackOverflowClient(this));
//        mWebView.setWebChromeClient(new StackOverflowClient());
    }


    private void loadLink(String soQuestionLink) {
        mWebView.loadUrl(soQuestionLink);
    }

    @Override
    public void startLoading() {
        mWebView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        mWebView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
