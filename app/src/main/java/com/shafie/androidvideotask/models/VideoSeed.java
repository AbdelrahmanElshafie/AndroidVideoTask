package com.shafie.androidvideotask.models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoSeed implements Parcelable {
    private final String id;
    private final String videoUrl;
    private final String userName;
    private final String description;

    public VideoSeed(String id, String videoUrl, String userName, String description) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.userName = userName;
        this.description = description;
    }

    protected VideoSeed(Parcel in) {
        id = in.readString();
        videoUrl = in.readString();
        userName = in.readString();
        description = in.readString();
    }

    public static final Creator<VideoSeed> CREATOR = new Creator<VideoSeed>() {
        @Override
        public VideoSeed createFromParcel(Parcel in) {
            return new VideoSeed(in);
        }

        @Override
        public VideoSeed[] newArray(int size) {
            return new VideoSeed[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(videoUrl);
        dest.writeString(userName);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
