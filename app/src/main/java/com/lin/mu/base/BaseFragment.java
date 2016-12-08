package com.lin.mu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lin on 2016/8/2.
 */
public abstract class BaseFragment extends Fragment {
    private View mRootvView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootvView = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, mRootvView);
        initView(mRootvView);
        
        return mRootvView;
    }

    protected abstract int getLayoutResource();

    protected abstract void initView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
