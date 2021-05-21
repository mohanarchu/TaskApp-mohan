package com.example.taskapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.taskapp.databinding.FragmentVideosBinding;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.master.exoplayer.MasterExoPlayerHelper;

import java.util.ArrayList;

public class VideosFragment extends Fragment {



    MasterExoPlayerHelper masterExoPlayerHelper;
    ArrayList<VideoData> videoData = new ArrayList<>();
    FragmentVideosBinding fragmentVideosBinding;
    VideosAdapter videosAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentVideosBinding = FragmentVideosBinding.inflate(getLayoutInflater());
        return fragmentVideosBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setArrayData();
        setRecyclerView();
        setExoplayer();
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
        masterExoPlayerHelper = new MasterExoPlayerHelper(getActivity(), R.id.frame,true,
                0,0,false,false,0,0);
        masterExoPlayerHelper.getPlayerView().setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
        masterExoPlayerHelper.makeLifeCycleAware(this);
        masterExoPlayerHelper.attachToRecyclerView( fragmentVideosBinding.videosRecycler);
        videosAdapter.notifyDataSetChanged();
    }
    void setRecyclerView() {
        videosAdapter = new VideosAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        fragmentVideosBinding.videosRecycler.setLayoutManager(gridLayoutManager);
        fragmentVideosBinding.videosRecycler.setAdapter(videosAdapter);
        videosAdapter.setList(videoData, results -> {

          startActivity(new Intent(getActivity(),VideoActivity.class));
        },false);

    }

}