package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.taskapp.databinding.ActivityVideoBinding;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.master.exoplayer.MasterExoPlayerHelper;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {


    ActivityVideoBinding binding;

    MasterExoPlayerHelper masterExoPlayerHelper;
    ArrayList<VideoData> videoData = new ArrayList<>();
    VideosAdapter videosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
    }
    void initUi() {
        setArrayData();
        setRecyclerView();
        setExoplayer();
        setStatusBar();
        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
    void setArrayData() {
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerBlazes.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerEscapes.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerFun.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerJoyrides.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ForBiggerMeltdowns.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/Sintel.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/SubaruOutbackOnStreetAndDirt.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/TearsOfSteel.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/WeAreGoingOnBullrun.jpg"));
        videoData.add(new VideoData("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4","http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/WhatCarCanYouGetForAGrand.jpg"));
    }

    void setExoplayer() {
        masterExoPlayerHelper = new MasterExoPlayerHelper(VideoActivity.this, R.id.frame,true,
                0,0,true,false,0,0);
        masterExoPlayerHelper.getPlayerView().setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
        masterExoPlayerHelper.makeLifeCycleAware(this);
        masterExoPlayerHelper.attachToRecyclerView( binding.videosRecycler);
        videosAdapter.notifyDataSetChanged();
    }

    void setRecyclerView() {
        videosAdapter = new VideosAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL,false);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView( binding.videosRecycler);
        binding.videosRecycler.setLayoutManager(linearLayoutManager);
        binding.videosRecycler.setAdapter(videosAdapter);
        videosAdapter.setList(videoData, results -> { },true);

    }
}