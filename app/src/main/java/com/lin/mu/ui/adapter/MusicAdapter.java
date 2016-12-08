package com.lin.mu.ui.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lin.mu.R;
import com.lin.mu.helper.MediaPlayerManager;
import com.lin.mu.model.Music;
import com.lin.mu.model.PhotoModel;
import com.lin.mu.views.VoiceCircleProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lin on 2016/8/2.
 */
public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Music.ResultBean.SongsBean> mList;
    private MediaPlayerManager playerManager;

    public MusicAdapter(Context ctx, List<Music.ResultBean.SongsBean> songs) {
        mContext = ctx;
        mList = songs;
        playerManager = MediaPlayerManager.getInstance(mContext);
        playerManager.initHandler(handler);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_music, parent, false);
        return new ViewHolderMusic(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolderMusic holder = (ViewHolderMusic) viewHolder;
        Music.ResultBean.SongsBean song = mList.get(position);
        if (!TextUtils.isEmpty(song.getName())) {
            holder.tvSong.setText(song.getName());
        }
        if (!TextUtils.isEmpty(song.getPicUrl())) {
            Glide.with(mContext).
                    load(song.getPicUrl()).
                    centerCrop().
                    dontAnimate().
                    placeholder(R.mipmap.ic_loading).
                    error(R.mipmap.ic_loading).
                    into(holder.imageView);
        } else if (!TextUtils.isEmpty(song.getAlbum().getPicUrl())) {
            Glide.with(mContext).
                    load(song.getAlbum().getPicUrl()).
                    centerCrop().
                    dontAnimate().
                    placeholder(R.mipmap.ic_loading).
                    error(R.mipmap.ic_loading).
                    into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.mipmap.eson);
        }
        holder.circleProgress.setTag(song);
        holder.circleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof VoiceCircleProgress) {
                    final VoiceCircleProgress masterLayout = (VoiceCircleProgress) v;
                    isPlayingView = masterLayout;
                    final Music.ResultBean.SongsBean song = (Music.ResultBean.SongsBean) v.getTag();
                    if (lastMasterLayout != null && lastMasterLayout != masterLayout) {
                        Music.ResultBean.SongsBean followUpTagLast = (Music.ResultBean.SongsBean) lastMasterLayout.getTag();
                        lastMasterLayout.setProgress(100);
                        lastMasterLayout.setStatus(VoiceCircleProgress.Status.End);
                        followUpTagLast.setPlaying(false);
                    }
                    MediaPlayer mediaPlayer = playerManager.getmMediaPlayer();
                    if (song.isPlaying() || (mediaPlayer != null && mediaPlayer.isPlaying())) {
                        playerManager.cancelTask();
                        playerManager.stopMediaPlayer();
                        playerManager.releaseMediaPlayer();
                    }
                    if (song.isPlaying()) {
                        masterLayout.setProgress(100);
                        masterLayout.setStatus(VoiceCircleProgress.Status.End);
                        song.setPlaying(false);
                    } else {
                        masterLayout.setProgress(0);
                        masterLayout.setStatus(VoiceCircleProgress.Status.Starting);
                        playerManager.startMediaPlayer(song.getAudio());
                        song.setPlaying(true);
                        playerManager.setPlayerCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                song.setPlaying(false);
                                playerManager.cancelTask();
                                playerManager.stopMediaPlayer();
                                playerManager.releaseMediaPlayer();
                                isPlayingView.setProgress(100);
                                isPlayingView.setStatus(VoiceCircleProgress.Status.End);
                            }
                        });
                    }
                    lastMasterLayout = masterLayout;
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }


    class ViewHolderMusic extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_photo)
        ImageView imageView;
        @BindView(R.id.tv_song)
        TextView tvSong;
        @BindView(R.id.circleProgress)
        VoiceCircleProgress circleProgress;

        public ViewHolderMusic(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void recycle() {
        playerManager.cancelTask();
        playerManager.releaseMediaPlayer();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    VoiceCircleProgress lastMasterLayout;
    VoiceCircleProgress isPlayingView;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what != -1) {
                MediaPlayer mediaPlayer = playerManager.getmMediaPlayer();
                if (mediaPlayer != null) {
                    int position = mediaPlayer.getCurrentPosition();
                    int duration = mediaPlayer.getDuration();
                    lastMasterLayout.setProgress(position * 100 / duration);
                }
                if (msg.what == 1) {
                    lastMasterLayout.setProgress(100);
                }
            } else {
                isPlayingView.setStatus(VoiceCircleProgress.Status.End);
            }
        }
    };

}
