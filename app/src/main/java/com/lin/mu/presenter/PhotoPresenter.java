package com.lin.mu.presenter;

import android.content.Context;
import android.util.Log;

import com.lin.mu.base.BasePresenter;
import com.lin.mu.http.HttpApi;
import com.lin.mu.http.ShowApiResponse;
import com.lin.mu.model.PhotoModel;
import com.lin.mu.contract.PhotoContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lin on 2016/8/2.
 */
public class PhotoPresenter extends BasePresenter<PhotoContract.View> implements PhotoContract.Presenter {
    private static final String TAG = "PhotoPresenter";
    private Context mContext;

    public PhotoPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(PhotoContract.View View) {
        super.attachView(View);
    }

    @Override
    public void loadListData(String type, int page) {
        HttpApi.getApi().getPhotos(type, page).enqueue(new Callback<ShowApiResponse<PhotoModel>>() {
            @Override
            public void onResponse(Call<ShowApiResponse<PhotoModel>> call, Response<ShowApiResponse<PhotoModel>> response) {
                if (response != null && response.isSuccessful()) {
                    try {
                        ShowApiResponse<PhotoModel> body = response.body();
                        List<PhotoModel.PictureBody> lists = body.showapi_res_body.pagebean.contentlist;
                        getView().canMore(body.showapi_res_body.pagebean);
                        if (lists != null && lists.size() > 0) {
                            getView().onLoadSuccess(lists);
                        }
                    } catch (Exception e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ShowApiResponse<PhotoModel>> call, Throwable t) {
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
    }

}
