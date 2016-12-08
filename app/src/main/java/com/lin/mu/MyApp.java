package com.lin.mu;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.lin.mu.http.HttpApi;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.vov.vitamio.Vitamio;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lin on 2016/8/2.
 */
public class MyApp extends Application {

    private static Context mContext;
    private static MyApp sMyApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        sMyApp = this;
        Vitamio.initialize(mContext);
        HttpApi.init();
    }

    public static MyApp getMyApp() {
        return sMyApp;
    }

    public static Context getContext() {
        return mContext;
    }

}
