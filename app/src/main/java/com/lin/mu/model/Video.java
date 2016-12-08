package com.lin.mu.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lin on 2016/12/1.
 */

public class Video implements Parcelable{

    /**
     * id : 618064687
     * url : http://www.meipai.com/media/618064687
     * cover_pic : http://mvimg1.meitudata.com/583f92fc328c66609.jpg!thumb320
     * screen_name : 🍥智恩baby🍥
     * caption : 宝宝蓝瘦香菇😝
     * avatar : http://mvavatar1.meitudata.com/581f49b3b67ae1020.jpg
     * plays_count : 24403
     * comments_count : 125
     * likes_count : 3495
     */

    private int id;
    private String url;
    private String cover_pic;
    private String screen_name;
    private String caption;
    private String avatar;
    private int plays_count;
    private int comments_count;
    private int likes_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover_pic() {
        return cover_pic;
    }

    public void setCover_pic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPlays_count() {
        return plays_count;
    }

    public void setPlays_count(int plays_count) {
        this.plays_count = plays_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.url);
        dest.writeString(this.cover_pic);
        dest.writeString(this.screen_name);
        dest.writeString(this.caption);
        dest.writeString(this.avatar);
        dest.writeInt(this.plays_count);
        dest.writeInt(this.comments_count);
        dest.writeInt(this.likes_count);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.id = in.readInt();
        this.url = in.readString();
        this.cover_pic = in.readString();
        this.screen_name = in.readString();
        this.caption = in.readString();
        this.avatar = in.readString();
        this.plays_count = in.readInt();
        this.comments_count = in.readInt();
        this.likes_count = in.readInt();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
