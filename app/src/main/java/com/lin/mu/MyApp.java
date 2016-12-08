package com.lin.mu;

import android.app.Application;
import android.content.Context;
import com.lin.mu.http.HttpApi;

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
        HttpApi.init();
    }

    public static MyApp getMyApp() {
        return sMyApp;
    }

    public static Context getContext() {
        return mContext;
    }

}
