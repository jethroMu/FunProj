package com.lin.mu.presenter;

import android.content.Context;

import com.lin.mu.base.BasePresenter;
import com.lin.mu.contract.MusicContract;
import com.lin.mu.http.HttpApi;
import com.lin.mu.model.Music;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 2016/12/1.
 */

public class MusicPresenter extends BasePresenter<MusicContract.View> implements MusicContract.Presenter {

    private Context mContext;

    public MusicPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(MusicContract.View View) {
        super.attachView(View);
    }

    @Override
    public void loadMusciData(String type, String key, String limit, String offset) {
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        params.put("s", key);
        params.put("limit", limit);
        params.put("offset", offset);
        HttpApi.getMusicApi().getMusic(params).enqueue(new Callback<Music>() {
            @Override
            public void onResponse(Call<Music> call, Response<Music> response) {
                Music body = response.body();
                if (body.getResult().getSongs() != null)
                    getView().onLoadSuccess(body.getResult().getSongs());

            }

            @Override
            public void onFailure(Call<Music> call, Throwable t) {

            }
        });
    }


    @Override
    public void detachView() {
        super.detachView();
    }

}
