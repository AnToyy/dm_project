/**
 * Copyright (C) 2014 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.mobstat.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.baidu.mobstat.StatService;
import com.baidu.mobstat.demo.R;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends Activity {

    private WebView mWebView;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.web_view);
        StatService.bindJSInterface(this, mWebView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();

        mWebView.loadUrl("file:///android_asset/mobstat.html");
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();

        StatService.onResume(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        super.onPause();

        StatService.onPause(this);
    }

}
