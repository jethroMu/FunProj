package com.lin.mu.http;

/**
 * Created by lin on 2016/11/28.
 */
public class AppConfig {

    private String host;
    private static String authToken;

    public AppConfig(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String authToken) {
        AppConfig.authToken = authToken;
    }
}
