package com.lin.mu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lin.mu.R;
import com.lin.mu.base.BaseFragment;
import com.lin.mu.contract.MusicContract;
import com.lin.mu.model.Music;
import com.lin.mu.presenter.MusicPresenter;
import com.lin.mu.ui.adapter.MusicAdapter;
import com.lin.mu.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lin on 2016/8/2.
 */
public class MusicFragment extends BaseFragment implements MusicContract.View {


    @BindView(R.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private MusicAdapter musicAdapter;
    private MusicPresenter presenter;
    private List<Music.ResultBean.SongsBean> songs;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_media;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songs = new ArrayList<>();
    }

    @Override
    protected void initView(View view) {
        setAdapter();
        setPresenter();
        addListener();
        presenter.loadMusciData("1", "陈奕迅", "30", "0");
    }

    private void addListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMusciData("1", "陈奕迅", "30", "0");
            }
        });
    }

    private void setAdapter() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.setHasFixedSize(true);
        musicAdapter = new MusicAdapter(getContext(), songs);
        mRecycleView.setAdapter(musicAdapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void setPresenter() {
        presenter = new MusicPresenter(getContext());
        presenter.attachView(this);
    }

    @Override
    public void onLoadSuccess(List<Music.ResultBean.SongsBean> lists) {
        if (lists != null && lists.size() > 0) {
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            songs.clear();
            songs.addAll(lists);
            musicAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        musicAdapter.recycle();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }
}
