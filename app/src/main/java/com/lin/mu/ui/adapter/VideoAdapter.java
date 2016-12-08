package com.lin.mu.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lin.mu.R;
import com.lin.mu.activity.WebViewActivity;
import com.lin.mu.model.Video;
import com.lin.mu.views.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 2016/8/2.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Video> mList;


    public VideoAdapter(Context ctx, List<Video> videos) {
        mContext = ctx;
        mList = videos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new ViewHolderVideo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ViewHolderVideo holder = (ViewHolderVideo) viewHolder;
        final Video video = mList.get(position);
        if (!TextUtils.isEmpty(video.getAvatar())) {
            Glide.with(mContext).
                    load(video.getAvatar()).
                    centerCrop().
                    dontAnimate().
                    placeholder(R.mipmap.ic_loading).
                    error(R.mipmap.ic_loading).
                    into(holder.circleImageview);
        } else {
            holder.circleImageview.setImageResource(R.mipmap.eson);
        }
        if (!TextUtils.isEmpty(video.getCover_pic())) {
            Glide.with(mContext).
                    load(video.getCover_pic()).
                    centerCrop().
                    dontAnimate().
                    placeholder(R.mipmap.ic_loading).
                    error(R.mipmap.ic_loading).
                    into(holder.ivContent);
        } else {
            holder.ivContent.setImageResource(R.mipmap.ic_navigation);
        }
        holder.tvName.setText(TextUtils.isEmpty(video.getScreen_name()) ? "" : video.getScreen_name());
        holder.tvContent.setText(TextUtils.isEmpty(video.getCaption()) ? "" : video.getCaption());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.start(mContext,video.getUrl(),video.getCaption());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }


    class ViewHolderVideo extends RecyclerView.ViewHolder {
        @BindView(R.id.circleImageview)
        CircleImageView circleImageview;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_content)
        ImageView ivContent;
        @BindView(R.id.tv_good)
        TextView tvGood;
        @BindView(R.id.tv_play)
        TextView tvPlay;
        @BindView(R.id.tv_comment)
        TextView tvComment;

        public ViewHolderVideo(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}