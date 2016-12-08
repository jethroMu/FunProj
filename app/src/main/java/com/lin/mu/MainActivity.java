package com.lin.mu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lin.mu.base.BaseActivity;
import com.lin.mu.helper.ActivityManager;
import com.lin.mu.ui.fragment.MusicFragment;
import com.lin.mu.ui.fragment.PhotoContainerFragment;
import com.lin.mu.ui.fragment.VideoContainerFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, Handler.Callback {


    @BindView(R.id.layout_container)
    FrameLayout layoutContainer;
    private Toolbar toolbar;
    private Fragment currentFragment;


    private boolean isAppExit;   //APP是否退出标志
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("图片");
        setSupportActionBar(toolbar);
        setNavigationView();
        if (currentFragment == null) {
            switchFragment(new PhotoContainerFragment());
        } else {
            switchFragment(currentFragment);
        }
    }

    private void switchFragment(Fragment fragment) {
        if (currentFragment == null || !fragment.getClass().getName().equals(currentFragment.getClass().getName())) {
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment).commit();
            currentFragment = fragment;
        }
    }

    private void setNavigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            } else {
                tryExitApp();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                toolbar.setTitle("音乐");
                switchFragment(new MusicFragment());
                break;
            case R.id.nav_gallery:
                toolbar.setTitle("图片");
                switchFragment(new PhotoContainerFragment());
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_video:
                toolbar.setTitle("视频");
                switchFragment(new VideoContainerFragment());
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
}
