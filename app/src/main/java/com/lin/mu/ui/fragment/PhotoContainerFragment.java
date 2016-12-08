package com.lin.mu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.lin.mu.R;
import com.lin.mu.base.BaseFragment;
import com.lin.mu.ui.adapter.PhotoPagerAdapter;

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
public class PhotoContainerFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Map<String, String> pics;
    List<String> titles;
    PhotoPagerAdapter photoPagerAdapter;
    List<Fragment> fragments;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_photo_container;
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
                PhotoFragment tabFragment = new PhotoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("extra", value);
                tabFragment.setArguments(bundle);
                fragments.add(tabFragment);
            }
        }
        photoPagerAdapter = new PhotoPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewpager.setAdapter(photoPagerAdapter);
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
        pics.put("明星", "2003");
        pics.put("美食", "6003");
        pics.put("社会", "1001");
        pics.put("娱乐", "3001");
        pics.put("美女", "4001");
        pics.put("萌宠", "6002");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
