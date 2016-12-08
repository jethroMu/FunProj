package com.lin.mu.contract;

import com.lin.mu.base.BaseView;
import com.lin.mu.model.PhotoModel;

import java.util.List;

/**
 * Created by lin on 2016/8/2.
 */
public interface PhotoContract{

    interface View extends BaseView{
        void onLoadSuccess(List<PhotoModel.PictureBody> lists);

        void canMore(PhotoModel.PageBean page);
    }

    interface Presenter {
        void loadListData(String type, int page);
    }
}
