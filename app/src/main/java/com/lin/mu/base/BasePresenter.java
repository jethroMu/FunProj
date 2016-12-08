package com.lin.mu.base;



/**
 * Created by lin on 16/10/24.
 */
public class BasePresenter<T extends BaseView> {

    private T mBaseView;

    public void attachView(T mvpView) {
        mBaseView = mvpView;
    }

    public void detachView() {
        mBaseView = null;
    }

    public boolean isViewAttached() {
        return mBaseView != null;
    }

    public T getView() {
        return mBaseView;
    }
}
