package com.stone.youtube_api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stone.youtube_api.R;
import com.stone.youtube_api.delegate.OnTapVideo;
import com.stone.youtube_api.dto.V_Data;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoListViewHolder> {

    ArrayList<V_Data> videoList;
    Context context;
    OnTapVideo tapVideo;

    public VideoListAdapter(Context context, OnTapVideo tapVideo) {
        this.context = context;
        this.tapVideo = tapVideo;
        videoList = new ArrayList<>();
    }

    public void setVideoList(ArrayList<V_Data> videoList) {
        this.videoList.addAll(videoList);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public VideoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item_layout, parent, false);
        return new VideoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoListViewHolder holder, int position) {



            final V_Data v_data = videoList.get(position);
            holder.txt.setText(v_data.getTitle());
            Glide.with(this.context)
                    .load(v_data.getImg())
                    .into(holder.img);
            //holder.img

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "ttyuiyui", Toast.LENGTH_SHORT).show();
                tapVideo.TapVideo(v_data.getVideoId());
            }
        });
        if (v_data.getNextPageToken().equals("noData")){
            holder.btn.setVisibility(View.GONE);
        }else {
            if (position == videoList.size()-1) {
                holder.btn.setVisibility(View.VISIBLE);
            }

        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapVideo.LoadV(v_data.getNextPageToken());
                holder.btn.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class VideoListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.vlist_img)
        ImageView img;
        @BindView(R.id.vlist_txt)
        TextView txt;
        @BindView(R.id.pl_more_pl_List)
        Button btn;

        public VideoListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//        img=itemView.findViewById(R.id.vlist_img);
//        txt=itemView.findViewById(R.id.vlist_txt);
        }
    }
}
