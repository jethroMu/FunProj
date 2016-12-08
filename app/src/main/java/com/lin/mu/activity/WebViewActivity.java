package com.lin.mu.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lin.mu.R;
import com.lin.mu.base.BaseActivity;
import com.lin.mu.utils.ToastUtils;

import java.util.Map;

public class WebViewActivity extends BaseActivity {
    private static String TAG = "WebViewActivity";
    public static final String URL = "url";
    public static final String TITLE = "title";
    private LinearLayout mRootView;
    protected WebView mWebView;
    private ProgressBar mProgress;
    private Toolbar toolbar;
    private String mUrl;
    private String mTitleString;

    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mTitleString = getIntent().getStringExtra(TITLE);
        initView();
        initData();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webview);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (!TextUtils.isEmpty(mTitleString)) {
            toolbar.setTitle(mTitleString);
            toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        setWebView();
    }

    private void initData() {
        mUrl = getIntent().getStringExtra(URL);
        if (TextUtils.isEmpty(mUrl)) {
            finish();
            return;
        }
        if (!mUrl.startsWith("http")) {
            mUrl = "http://" + mUrl;
        }
        Map<String, String> headers = null;
        if (mWebView != null)
            mWebView.loadUrl(mUrl, headers);
        else {
            ToastUtils.showShortToast(this, "网页出现异常，请稍后重试");
            finish();
            return;
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    private void setWebView() {
        WebSettings ws = mWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);
        ws.setJavaScriptEnabled(true);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setDomStorageEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgress.setProgress(newProgress);
            mProgress.postInvalidate();
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(final WebView view, String url) {
            super.onPageFinished(view, url);
            mProgress.setVisibility(View.GONE);
            mWebView.loadUrl("javascript:window.javascript.setTitle( $.utils.getAppTitle() )");
        }


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!TextUtils.isEmpty(url)) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            }
            if (url.contains("/back-app")) {
                WebViewActivity.this.finish();
                return true;
            }
            return false;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
        }
        super.onDestroy();
    }

}
