package com.lin.mu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.lin.mu.R;
import com.lin.mu.base.BaseFragment;
import com.lin.mu.contract.PhotoContract;
import com.lin.mu.model.PhotoModel;
import com.lin.mu.ui.adapter.PhotoAdapter;
import com.lin.mu.presenter.PhotoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lin on 2016/8/2.
 */
public class PhotoFragment extends BaseFragment implements PhotoContract.View, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.mRecycleView)
    RecyclerView mRecyclerView;
    @BindView(R.id.container_error)
    View errorView;
    @BindView(R.id.container_no_net)
    View noNetView;
    @BindView(R.id.container_empty)
    View emptyView;

    private PhotoPresenter presenter;
    private PhotoAdapter photoAdapter;
    private List<PhotoModel.PictureBody> photos;
    private int mPageNum = 1;
    private boolean canLoadMore = false;
    private String id;
    private StaggeredGridLayoutManager mGridViewLayoutManager;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_photo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photos = new ArrayList<>();
        Bundle bundle = getArguments();
        id = (String) bundle.get("extra");
    }

    @Override
    protected void initView(View view) {
        setAdapter();
        setPresenter();
        addListener();
        if (!TextUtils.isEmpty(id)) {
            loadPictures(id, true);
        }
    }


    private void addListener() {
        photoAdapter.setOnItemActionListener(new PhotoAdapter.OnItemActionListener() {
            @Override
            public void onItemClickListener(View v, int pos) {

            }

            @Override
            public boolean onItemLongClickListener(View v, int pos) {
                return false;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(R.color.primary_dark);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    if (canLoadMore) {
                        loadPictures(id, false);
                    }
                }
            }
        });
    }

    private void setAdapter() {
        photoAdapter = new PhotoAdapter(getContext());
        mGridViewLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mGridViewLayoutManager);
        mRecyclerView.setAdapter(photoAdapter);
    }

    private void setPresenter() {
        presenter = new PhotoPresenter(getContext());
        presenter.attachView(this);
    }

    @Override
    public void onLoadSuccess(List<PhotoModel.PictureBody> lists) {
        if (lists != null && lists.size() > 0) {
            if (mPageNum == 1) {
                photos.clear();
            }
            photos.addAll(lists);
            photoAdapter.setDatas(photos);
            if (isRefresh) {
                if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                mRecyclerView.smoothScrollToPosition(0);
            }
        } else {
            if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void canMore(PhotoModel.PageBean page) {
        if (page == null) {
            return;
        }
        if (Integer.valueOf(page.allPages) > Integer.valueOf(page.currentPage)) {
            canLoadMore = true;
            photoAdapter.canLoad(true);
        } else {
            canLoadMore = false;
            photoAdapter.canLoad(false);
        }
    }

    boolean isRefresh = true;


    private void loadPictures(String id, boolean isRresh) {
        if (isRresh) {
            mPageNum = 1;
            isRefresh = true;
        } else {
            mPageNum++;
            isRefresh = false;
        }
        presenter.loadListData(id, mPageNum);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadPictures(id, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        }
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
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
