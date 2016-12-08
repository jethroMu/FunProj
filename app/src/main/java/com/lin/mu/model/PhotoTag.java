package com.lin.mu.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lin on 2016/11/25.
 */
public class PhotoTag implements Parcelable {
    private  int id;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public PhotoTag() {
    }

    protected PhotoTag(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<PhotoTag> CREATOR = new Creator<PhotoTag>() {
        @Override
        public PhotoTag createFromParcel(Parcel source) {
            return new PhotoTag(source);
        }

        @Override
        public PhotoTag[] newArray(int size) {
            return new PhotoTag[size];
        }
    };
}
