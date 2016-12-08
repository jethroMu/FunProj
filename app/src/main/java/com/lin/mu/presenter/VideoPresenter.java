package com.lin.mu.presenter;

import android.content.Context;

import com.lin.mu.base.BasePresenter;
import com.lin.mu.contract.MusicContract;
import com.lin.mu.contract.VideoContract;
import com.lin.mu.http.HttpApi;
import com.lin.mu.model.Music;
import com.lin.mu.model.Video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 2016/12/1.
 */

public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter {

    private Context mContext;

    public VideoPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(VideoContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void loadViewoData(String id, String topName, int page, int count) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("topic_name", topName);
        params.put("page", String.valueOf(page));
        params.put("count", String.valueOf(count));
        HttpApi.getVideoApi().getVideo(params).enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                List<Video> videos = response.body();
                if (videos != null) {
                    getView().onLoadSuccess(videos);
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                getView().showError();
            }
        });
    }
}
