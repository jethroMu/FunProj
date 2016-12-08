package com.lin.mu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.lin.mu.base.BaseActivity;
import com.lin.mu.helper.ActivityManager;
import com.lin.mu.ui.fragment.MusicFragment;
import com.lin.mu.ui.fragment.NewsFragment;
import com.lin.mu.ui.fragment.OtherFragment;
import com.lin.mu.ui.fragment.PhotoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Handler.Callback, TabHost.OnTabChangeListener {

    private boolean isAppExit;   //APP是否退出标志
    private Handler handler = new Handler();
    private FragmentTabHost mTabHost;

    private int mImageArray[] = {R.mipmap.ic_photo,
            R.mipmap.ic_news, R.mipmap.ic_music,
            R.mipmap.ic_other};

    private int mPressImageArray[] = {R.mipmap.ic_photo1,
            R.mipmap.ic_news1, R.mipmap.ic_music1,
            R.mipmap.ic_other1};

    private String mTextArray[] = {"图片", "新闻", "媒体", "其他"};

    public List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        fragments.add(new PhotoFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MusicFragment());
        fragments.add(new OtherFragment());
        findTabs();
    }

    private void findTabs() {
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mTabHost.setOnTabChangedListener(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.layout_container);
        int count = fragments.size();
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments.get(i).getClass(), null);
            mTabHost.getTabWidget().setDividerDrawable(null);
            mTabHost.setTag(i);
        }
    }

    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);
        return view;
    }
 

    @Override
    public void onBackPressed() {
        tryExitApp();
    }

    private void tryExitApp() {
        if (!isAppExit) {
            isAppExit = true;
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_LONG)
                    .show();
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            ActivityManager.getInstance().finishAllActivity();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == 0) {
            isAppExit = false;
            return true;
        }
        return false;
    }

    @Override
    public void onTabChanged(String tabId) {
        TabWidget tabw = mTabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.imageview);
            if (i == mTabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.textview)).setTextColor(getResources().getColor(R.color.tab_press));
                iv.setImageResource(mPressImageArray[i]);
            } else {
                ((TextView) view.findViewById(R.id.textview)).setTextColor(getResources().getColor(R.color.tab_normal));
                iv.setImageResource(mImageArray[i]);
            }
        }
    }
}
