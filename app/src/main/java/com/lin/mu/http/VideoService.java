package com.lin.mu.http;

import com.lin.mu.Constants;
import com.lin.mu.model.Video;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by lin on 2016/12/1.
 */

public interface VideoService {

    @GET(Constants.MEIPAI_WPI)
    Call<List<Video>> getVideo(@QueryMap Map<String, String> map);
}
