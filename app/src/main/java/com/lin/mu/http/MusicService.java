package com.lin.mu.http;

import com.lin.mu.Constants;
import com.lin.mu.model.Music;
import com.lin.mu.model.PhotoModel;
import com.lin.mu.model.PhotoType;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by lin on 2016/8/25.
 */
public interface MusicService {


    @GET(Constants.WANGYI_WPI)
    Call<Music> getMusic(@QueryMap Map<String, String> map);

}
