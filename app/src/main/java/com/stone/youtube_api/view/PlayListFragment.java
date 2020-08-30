package com.stone.youtube_api.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stone.youtube_api.R;
import com.stone.youtube_api.adapter.PlayListAdapter;
import com.stone.youtube_api.delegate.LoadPlayLists;
import com.stone.youtube_api.delegate.OnTapVideo;
import com.stone.youtube_api.dto.PlayLists;
import com.stone.youtube_api.model.AppModel;

import java.util.ArrayList;

public class PlayListFragment extends Fragment implements OnTapVideo, LoadPlayLists {

    @BindView(R.id.play_list_rc_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    AppModel appModel;
    PlayListAdapter adapter;
    public PlayListFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(this,view);
        adapter=new PlayListAdapter(getContext(),this);
        appModel=AppModel.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        appModel.LoadPlayList(getContext(),this);
    }

    @Override
    public void TapVideo(String videoId) {
        startActivity(PlayListItemActivity.goToPlayListItemActivity(getContext(),videoId));

    }

    @Override
    public void LoadV(String pageToken) {
        appModel.LoadMorePlayList(getContext(),this,pageToken);
    }

    @Override
    public void LoadSuccess(String message) {

    }

    @Override
    public void LoadResult(ArrayList<PlayLists> videoList) {
        adapter.setPlayLists(videoList);
    }

    @Override
    public void LoadFail(String message) {

    }
}