package com.lin.mu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lin.mu.R;
import com.lin.mu.base.BaseFragment;
import com.lin.mu.ui.adapter.PhotoPagerAdapter;
import com.lin.mu.ui.adapter.VideoPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 2016/11/28.
 */
public class VideoContainerFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Map<String, String> pics;
    List<String> titles;
    VideoPagerAdapter videoPagerAdapter;
    List<Fragment> fragments;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        initData();
    }

    @Override
    protected void initView(View view) {
        if (pics.size() > 0) {
            titles.clear();
            fragments.clear();
            for (Map.Entry<String, String> vo : pics.entrySet()) {
                String title = vo.getKey();
                titles.add(title);
                String value = vo.getValue();
                VideoFragment tabFragment = new VideoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("extra", value);
                tabFragment.setArguments(bundle);
                fragments.add(tabFragment);
            }
        }
        videoPagerAdapter = new VideoPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewpager.setAdapter(videoPagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                viewpager.setCurrentItem(tab.getPosition(), true);
            }
        });
    }

    private void initData() {
        pics = new LinkedHashMap<>();
        pics.put("热门", "1");
        pics.put("搞笑", "13");
        pics.put("二次元", "193");
        pics.put("女神", "19");
        pics.put("音乐", "62");
        pics.put("逗比", "64");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
