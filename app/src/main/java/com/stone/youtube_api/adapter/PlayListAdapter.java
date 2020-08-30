package com.stone.youtube_api.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.stone.youtube_api.R;
import com.stone.youtube_api.delegate.OnTapVideo;
import com.stone.youtube_api.dto.PlayLists;
import com.stone.youtube_api.dto.V_Data;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder> {
    ArrayList<PlayLists> playLists;
    Context context;
    OnTapVideo tapVideo;

    public PlayListAdapter(Context context, OnTapVideo tapVideo) {
        playLists=new ArrayList<>();
        this.context = context;
        this.tapVideo = tapVideo;
    }

    public void setPlayLists(ArrayList<PlayLists> playLists) {
        this.playLists.addAll(playLists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_item_layout, parent, false);
        return new PlayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayListViewHolder holder, final int position) {

       // holder.item.setVisibility(View.VISIBLE);
        final PlayLists v_data = playLists.get(position);

        Glide.with(this.context)
                .load(v_data.getListImgUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        notifyItemRemoved(position);
                        playLists.remove(position);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.img);
        //holder.img

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, v_data.getListId(), Toast.LENGTH_SHORT).show();
                tapVideo.TapVideo(v_data.getListId());
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapVideo.TapVideo(v_data.getListId());
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tapVideo.LoadV(v_data.getNextPageToken());
                holder.btn.setVisibility(View.GONE);

            }
        });
        holder.itemCount.setText(v_data.getListItemCount()+"");
        holder.title.setText(v_data.getListTitle());
        if (position == playLists.size()-1) {
            holder.btn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return playLists.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    public class PlayListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pl_img)
        ImageView img;
        @BindView(R.id.pl_title)
        TextView title;
        @BindView(R.id.pl_cardView)
        CardView cardView;
        @BindView(R.id.pl_item_count)
        TextView itemCount;
        @BindView(R.id.pl_more_pl_List)
        Button btn;



        public PlayListViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }
}
