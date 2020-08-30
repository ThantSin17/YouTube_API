package com.stone.youtube_api.model;

import android.content.Context;
import android.widget.Toast;

import com.stone.youtube_api.delegate.GetAllVideo;
import com.stone.youtube_api.delegate.LoadPlayLists;
import com.stone.youtube_api.utils.AppUtils;

import java.util.concurrent.ExecutionException;

import butterknife.internal.Utils;

public class AppModel {
 static AppModel Instance;

    public static AppModel getInstance() {
        if (Instance==null){
            Instance=new AppModel();
        }
        return Instance;
    }
    public static void LoadChannel(Context context,GetAllVideo getAll){
        VideoListLoader vdl=new VideoListLoader(context,getAll);
        vdl.execute(AppUtils.testUrl);
    }
    public static void LoadMoreVideo(Context context,GetAllVideo getAll,String nextPage){
        VideoListLoader vdl=new VideoListLoader(context,getAll);
        vdl.execute(AppUtils.testUrl+"&pageToken="+nextPage);
    }
    public static void LoadPlayList(Context context, LoadPlayLists loader){
        PlayListLoader pll=new PlayListLoader(context,loader);
        pll.execute(AppUtils.playlistUrl);

    }
    public static void LoadMorePlayList(Context context,LoadPlayLists loadPlayLists,String nextPage) {
        PlayListLoader pll=new PlayListLoader(context,loadPlayLists);
        pll.execute(AppUtils.playlistUrl+"&pageToken="+nextPage);
    }
    public static void LoadPlayListItems(Context context,GetAllVideo getAll,String playListId,String nextPage){
        PlayListItemLoader loader=new PlayListItemLoader(context,getAll);
        loader.execute(AppUtils.playlistItemUrl+playListId+nextPage);

    }

}
