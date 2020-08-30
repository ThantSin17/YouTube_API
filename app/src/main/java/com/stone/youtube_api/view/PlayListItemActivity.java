package com.stone.youtube_api.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.stone.youtube_api.R;
import com.stone.youtube_api.adapter.VideoListAdapter;
import com.stone.youtube_api.delegate.GetAllVideo;
import com.stone.youtube_api.delegate.OnTapVideo;
import com.stone.youtube_api.dto.V_Data;
import com.stone.youtube_api.model.AppModel;
import com.stone.youtube_api.utils.AppUtils;

import java.util.ArrayList;

public class PlayListItemActivity extends AppCompatActivity implements OnTapVideo, GetAllVideo {
    @BindView(R.id.item_rc)
    RecyclerView recyclerView;
    AppModel appModel;
    VideoListAdapter adapter;
    static String playListID;
    public static Intent goToPlayListItemActivity(Context context, String playListId) {
        playListID=playListId;
        Intent intent = new Intent(context, PlayListItemActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list_item);
        ButterKnife.bind(this);
        appModel= AppModel.getInstance();
        adapter=new VideoListAdapter(this,this);
        appModel.LoadPlayListItems(this,this,playListID,"");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void TapVideo(String videoId) {
        startActivity(VideoView.goToViewVideo(this,videoId));
    }

    @Override
    public void LoadV(String pageToken) {
        appModel.LoadPlayListItems(this,this,playListID,"&pageToken="+pageToken);
    }

    @Override
    public void Success(String message) {

    }

    @Override
    public void SuccessResult(ArrayList<V_Data> videoList) {
        adapter.setVideoList(videoList);
    }

    @Override
    public void Fail(String message) {

    }
}