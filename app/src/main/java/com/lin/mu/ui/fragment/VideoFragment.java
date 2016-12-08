package com.lin.mu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.lin.mu.R;
import com.lin.mu.base.BaseFragment;
import com.lin.mu.contract.VideoContract;
import com.lin.mu.model.Video;
import com.lin.mu.presenter.VideoPresenter;
import com.lin.mu.ui.adapter.VideoAdapter;
import com.lin.mu.views.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lin on 2016/12/1.
 */

public class VideoFragment extends BaseFragment implements VideoContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private VideoAdapter adapter;
    private String id;
    private int mPageNum = 1;
    private int prePage = 20;
    private VideoPresenter presenter;
    List<Video> videos;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videos = new ArrayList<>();
        Bundle bundle = getArguments();
        id = (String) bundle.get("extra");
    }

    @Override
    protected void initView(View view) {
        setAdapter();
        setPresenter();
        addListener();
        if (id != null) {
            loadVideo(true);
        }
    }

    private void setPresenter() {
        presenter = new VideoPresenter(getContext());
        presenter.attachView(this);
    }

    @Override
    public void onLoadSuccess(List<Video> lists) {
        if (lists != null && lists.size() > 0) {
            videos.clear();
            videos.addAll(lists);
            adapter.notifyDataSetChanged();
            if (isRefresh) {
                if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                mRecycleView.smoothScrollToPosition(0);
            }
        }
    }

    private void setAdapter() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new VideoAdapter(getContext(), videos);
        mRecycleView.setAdapter(adapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void addListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                int lastChildBottom = lastChildView.getBottom();
                int recyclerBottom = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                int lastPosition = recyclerView.getLayoutManager().getPosition(lastChildView);
                if (lastChildBottom == recyclerBottom && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                    loadVideo(false);
                }
            }
        });

    }

    boolean isRefresh = true;

    private void loadVideo(boolean onRefresh) {
        if (onRefresh) {
            mPageNum = 1;
            isRefresh = true;
        } else {
            mPageNum++;
            isRefresh = false;
        }
        presenter.loadViewoData(id, "", mPageNum, prePage);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
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

    @Override
    public void onRefresh() {
        if (!TextUtils.isEmpty(id)) {
            loadVideo(true);
        }
    }
}
