package com.stone.youtube_api.view;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.stone.youtube_api.R;
import com.stone.youtube_api.adapter.VideoListAdapter;
import com.stone.youtube_api.delegate.GetAllVideo;
import com.stone.youtube_api.delegate.OnTapVideo;
import com.stone.youtube_api.dto.V_Data;
import com.stone.youtube_api.model.AppModel;

import java.util.ArrayList;

public class AllVideoFragment extends Fragment implements GetAllVideo, OnTapVideo {

    @BindView(R.id.main_rc_view)
    RecyclerView recyclerView;
    AppModel appModel;
    VideoListAdapter adapter;
    ArrayList<V_Data> mvideoList;
    ProgressDialog pd;
    private Unbinder unbinder;
    public AllVideoFragment() {
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
        return inflater.inflate(R.layout.fragment_all_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder=ButterKnife.bind(this,view);
        //recyclerView=findViewById(R.id.main_rc_view);
        pd=new ProgressDialog(getContext());
        pd.setMessage("loading .....");
        //pd.show();
        adapter=new VideoListAdapter(getContext(),this);
        appModel=AppModel.getInstance();


        //  mvideoList=new ArrayList<>();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    @Override
    public void Success(String message) {
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void SuccessResult(ArrayList<V_Data> videoList) {
        adapter.setVideoList(videoList);

        //  Toast.makeText(this, videoList.get(50).getTitle(), Toast.LENGTH_SHORT).show();
        // pd.dismiss();
    }

    @Override
    public void Fail(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void TapVideo(String videoId) {
        startActivity(VideoView.goToViewVideo(getContext(),videoId));
    }

    @Override
    public void LoadV(String pageToken) {
        appModel.LoadMoreVideo(getContext(),this,pageToken);
    }

    @Override
    public void onResume() {
        super.onResume();
        appModel.LoadChannel(getContext(),this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}