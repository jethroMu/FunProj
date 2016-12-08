package com.lin.mu.contract;

import com.lin.mu.base.BaseView;
import com.lin.mu.model.Music;
import com.lin.mu.model.Video;

import java.util.List;

/**
 * Created by lin on 2016/8/2.
 */
public interface VideoContract {

    interface View extends BaseView {
        void onLoadSuccess(List<Video> lists);
    }

    interface Presenter {
        void loadViewoData(String id, String topName, int page, int count);
    }
}
