package com.stone.youtube_api.dto;

public class PlayListItem {
    String title;
    String description;
    String imgUrl;
    String videoId;

    public PlayListItem(String title, String description, String imgUrl, String videoId) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.videoId = videoId;
    }

    public PlayListItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
