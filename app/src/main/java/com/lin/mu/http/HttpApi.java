package com.lin.mu.http;

import com.lin.mu.Constants;

/**
 * Created by lin on 2016/8/28.
 */
public class HttpApi {

    private static APIService apiService;
    private static MusicService musicService;
    private static VideoService videoService;

    public static void init() {
        AppConfig appConfig = new AppConfig(Constants.BAIDU_API);
        apiService = RetrofitBuilder.build(appConfig, APIService.class);
        AppConfig musicConfig = new AppConfig(Constants.WANGYI_WPI);
        musicService = RetrofitBuilder.build(musicConfig, MusicService.class);
        AppConfig videoCondig = new AppConfig(Constants.MEIPAI_WPI);
        videoService = RetrofitBuilder.build(videoCondig, VideoService.class);
    }

    public static APIService getApi() {
        if (apiService == null) {
            init();
        }
        return apiService;
    }

    public static MusicService getMusicApi() {
        if (musicService == null) {
            init();
        }
        return musicService;
    }

    public static VideoService getVideoApi() {
        if (videoService == null) {
            init();
        }
        return videoService;
    }

}
