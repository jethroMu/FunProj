package com.lin.mu.helper;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by lin on 2016/8/4.
 */

public class MediaPlayerManager {

    private static final String TAG = "MediaPlayerManager";
    private Context mContext;
    private Timer timer;
    private Handler handler;
    private TimerTask task;


    private MediaPlayerManager(Context ctx) {
        mContext = ctx;
        initMediaPlayer();
        initSensor();
    }

    public static MediaPlayerManager getInstance(Context ctx) {
        return new MediaPlayerManager(ctx);
    }

    public void initHandler(Handler handler) {
        this.handler = handler;
    }


    public void startMediaPlayer(String path) {
        if (mMediaPlayer == null) {
            initMediaPlayer();
        }
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            Log.d(TAG, "startMediaPlayer error");
        }
    }

    public void stopMediaPlayer() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void registerSensorListener() {
        if (!isSensorListenerRegistered) {
            mSensorManager.registerListener(mSensorListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
            isSensorListenerRegistered = true;
        }
    }

    public void unregisterSensorListener() {
        if (isSensorListenerRegistered) {
            mSensorManager.unregisterListener(mSensorListener);
            isSensorListenerRegistered = false;
        }
    }

    public void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        unregisterSensorListener();
    }

    /**
     * 设置播放器播放完成后的监听事件
     */
    public void setPlayerCompletionListener(OnCompletionListener listener) {
        if (listener != null) {
            mCompletionListener = listener;
            if (mMediaPlayer == null) {
                initMediaPlayer();
            }
            mMediaPlayer.setOnCompletionListener(listener);
        }
    }

    public void setOnMediaPlayerListener(OnMediaPlayerListener onMediaPlayerListener) {
        this.onMediaPlayerListener = onMediaPlayerListener;
    }

    OnMediaPlayerListener onMediaPlayerListener;

    public interface OnMediaPlayerListener {

        void onStart(MediaPlayer mp);
    }

    // =================== 播放电话录音 ========================
    private MediaPlayer mMediaPlayer;
    private OnCompletionListener mCompletionListener;

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                mMediaPlayer.start();
                startTimer();
                if (onMediaPlayerListener != null) {
                    onMediaPlayerListener.onStart(mp);
                }
            }
        });
        if (mCompletionListener != null) {
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
        }
    }

    private void startTimer() {
        if (this.handler == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer(true);
        }
//        timer.cancel();
        task = new TimerTask() {
            @Override
            public void run() {
                if (mMediaPlayer == null) {
                    cancelTask();
                    return;
                }
                if (mMediaPlayer.isPlaying()) {
                    handler.sendEmptyMessage(0);
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        };
        timer.schedule(task, 0, 50);
    }

    public void cancelTask() {
        if (task != null) {
            task.cancel();
        }
    }


    public MediaPlayer getmMediaPlayer() {
        return mMediaPlayer;
    }
    // =============== 距离传感器 =================

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private boolean isSensorListenerRegistered = false;
    private AudioManager mAudioManager;
    private SensorEventListener mSensorListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.values[0] == mSensor.getMaximumRange()) {
                mAudioManager.setMode(AudioManager.MODE_NORMAL);
            } else {
                mAudioManager.setMode(AudioManager.MODE_IN_CALL);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void initSensor() {
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        registerSensorListener();
    }
}
