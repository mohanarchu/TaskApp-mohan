package com.example.taskapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskapp.databinding.TimelineViewBinding;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.master.exoplayer.ExoPlayerHelper;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<VideoData> resultsList;
    boolean fullView;
    DiscoverViewSelectedInterface discoverViewSelectedInterface;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TimelineViewBinding timelineViewBinding = TimelineViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(timelineViewBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
            ViewHolder holder = (ViewHolder) holders;
            if(!fullView) {
                holder.timelineViewBinding.frame.setMute(true);
                holder.itemView.getLayoutParams().width = (int) (ScreenUtils.getScreenWidth(holder.itemView.getContext()) / 3);
                holder.itemView.getLayoutParams().height = (int) (ScreenUtils.getScreenHeight(holder.itemView.getContext()) / 3.5);
            } else {
                holder.timelineViewBinding.countLayout.setVisibility(View.GONE);
            }
            holder.timelineViewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    discoverViewSelectedInterface.selecedItem(resultsList.get(position));
                }
            });
            holder.timelineViewBinding.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    discoverViewSelectedInterface.selecedItem(resultsList.get(position));
                }
            });
            holder.timelineViewBinding.frame.setUrl(resultsList.get(position).getVideoUrl());

//            holder.timelineViewBinding.frame.getPlayerView().getPlayer().v
            holder.timelineViewBinding.frame.setImageView(holder.timelineViewBinding.image);
            Glide.with(holder.itemView.getContext()).load(resultsList.get(position).getThumbnail()).into(holder.timelineViewBinding.image);
            holder.timelineViewBinding.frame.setOnClickListener(view -> {

            });
            holder.timelineViewBinding.frame.setListener(new ExoPlayerHelper.Listener() {
                @Override
                public void onPlayerReady() {

                }
                @Override
                public void onStart() {

                }

                @Override
                public void onStop() {

                }
                @Override
                public void onProgress(long l) {

                }
                @Override
                public void onError(@Nullable ExoPlaybackException e) {

                }
                @Override
                public void onBuffering(boolean b) {

                }
                @Override
                public void onToggleControllerVisible(boolean b) {

                }
            });

    }

    @Override
    public int getItemCount() {
        return resultsList == null ? 0 : resultsList.size();
    }

    public void setList(List<VideoData> resultsList,DiscoverViewSelectedInterface discoverViewSelectedInterface,boolean isFullView) {
        this.resultsList = resultsList;
        this.discoverViewSelectedInterface = discoverViewSelectedInterface;
        this.fullView = isFullView;
    }


    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
//        PlayerViewAdapter.releaseRecycledPlayers(holder.getAdapterPosition());
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return resultsList.size();
    }


    public interface DiscoverViewSelectedInterface {
        void selecedItem(VideoData results);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TimelineViewBinding timelineViewBinding;
        public ViewHolder(@NonNull TimelineViewBinding itemView) {
            super(itemView.getRoot());
            timelineViewBinding = itemView;

        }

    }


}
