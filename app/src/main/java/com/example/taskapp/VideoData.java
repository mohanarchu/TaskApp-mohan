package com.example.taskapp;

public class VideoData {

    String thumbnail;
    String videoUrl;
    VideoData(String videoUrl,String thumbnail) {
        this.thumbnail = thumbnail;
        this.videoUrl= videoUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
