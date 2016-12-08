package com.lin.mu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by lin on 2016/11/25.
 */
public class PhotoTypeItem implements Parcelable{
    private List<PhotoTag> tagList;

    private String name;
    public List<PhotoTag> getTagList() {
        return tagList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTagList(List<PhotoTag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.tagList);
        dest.writeString(this.name);
    }

    public PhotoTypeItem() {
    }

    protected PhotoTypeItem(Parcel in) {
        this.tagList = in.createTypedArrayList(PhotoTag.CREATOR);
        this.name = in.readString();
    }

    public static final Creator<PhotoTypeItem> CREATOR = new Creator<PhotoTypeItem>() {
        @Override
        public PhotoTypeItem createFromParcel(Parcel source) {
            return new PhotoTypeItem(source);
        }

        @Override
        public PhotoTypeItem[] newArray(int size) {
            return new PhotoTypeItem[size];
        }
    };
}
