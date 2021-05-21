package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.taskapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initUI();
    }

    void initUI() {
        Glide.with(MainActivity.this)
                .load(getResources().getDrawable(R.drawable.user_icon))
                .transform(new CircleCrop())
                .into(activityMainBinding.userImage);
        setStatusBar();
        initListeners();
        setViewPager();
        toggleView(true);
    }

    void initListeners() {
        activityMainBinding.videos.setOnClickListener(view -> toggleView(true));
        activityMainBinding.likedVideos.setOnClickListener(view -> toggleView(false));
        activityMainBinding.toolbar.more.setOnClickListener(view -> showReport());
    }

    void setViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        VideosFragment videosFragment = new VideosFragment();
        VideosFragment likesFragment = new VideosFragment();
        adapter.addFragment(videosFragment,"");
        adapter.addFragment(likesFragment,"");
        activityMainBinding.timelineTabViewpager.setAdapter(adapter);
        activityMainBinding.timelineTabViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toggleView(position == 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    void toggleView(boolean isVideos) {
        if(isVideos) {
            activityMainBinding.videos.setAlpha(1f);
            activityMainBinding.likedVideos.setAlpha(0.5f);
            activityMainBinding.timelineTabViewpager.setCurrentItem(0);
        } else {
            activityMainBinding.videos.setAlpha(0.5f);
            activityMainBinding.likedVideos.setAlpha(1f);
            activityMainBinding.timelineTabViewpager.setCurrentItem(1);
        }
    }

    void setStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        }
    }
    private void showReport() {
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.custom_pop, activityMainBinding.getRoot(),false);
        PopupWindow reportPop = new PopupWindow(MainActivity.this);
        reportPop.setContentView(layout);
        reportPop.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        reportPop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        reportPop.setFocusable(true);
        int[] location = new int[2];
        activityMainBinding.toolbar.more.getLocationOnScreen(location);
        int x = -location[0] +40;
        int y = location[1] + 40;
        reportPop.setBackgroundDrawable(new BitmapDrawable());
        reportPop.showAtLocation(layout, Gravity.TOP | Gravity.END,   x,  y);
    }
}