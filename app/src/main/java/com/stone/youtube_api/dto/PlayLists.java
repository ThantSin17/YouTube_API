package com.stone.youtube_api.dto;

public class PlayLists {
    String nextPageToken;
    String ListId;
    String listTitle;
    String listDescription;
    String listImgUrl;
    int listItemCount;


    public PlayLists(String nextPageToken, String listId, String listTitle, String listDescription, String listImgUrl, int listItemCount) {
        this.nextPageToken = nextPageToken;
        ListId = listId;
        this.listTitle = listTitle;
        this.listDescription = listDescription;
        this.listImgUrl = listImgUrl;
        this.listItemCount = listItemCount;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public PlayLists() {
    }

    public String getListId() {
        return ListId;
    }

    public void setListId(String listId) {
        ListId = listId;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getListDescription() {
        return listDescription;
    }

    public void setListDescription(String listDescription) {
        this.listDescription = listDescription;
    }

    public String getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(String listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public void setListItemCount(int listItemCount) {
        this.listItemCount = listItemCount;
    }
}
