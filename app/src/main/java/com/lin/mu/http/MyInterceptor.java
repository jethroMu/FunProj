package com.lin.mu.http;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lin on 2016/8/21.
 */
public class MyInterceptor implements Interceptor {

    //自定义拦截器   添加Header
    private Map<String, String> headers;

    public MyInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }


    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }
        return chain.proceed(builder.build());
    }
}
