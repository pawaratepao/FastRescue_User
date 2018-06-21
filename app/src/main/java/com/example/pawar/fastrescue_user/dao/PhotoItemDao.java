package com.example.pawar.fastrescue_user.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 04-Feb-17.
 */

public class PhotoItemDao implements Parcelable {
    @SerializedName("news_id")
    @Expose
    private String newsId;
    @SerializedName("news_topic")
    @Expose
    private String newsTopic;
    @SerializedName("news_detail")
    @Expose
    private String newsDetail;
    @SerializedName("news_filename")
    @Expose
    private String newsFilename;
    @SerializedName("news_date")
    @Expose
    private String newsDate;

    protected PhotoItemDao(Parcel in) {
        newsId = in.readString();
        newsTopic = in.readString();
        newsDetail = in.readString();
        newsFilename = in.readString();
        newsDate = in.readString();
    }

    public static final Creator<PhotoItemDao> CREATOR = new Creator<PhotoItemDao>() {
        @Override
        public PhotoItemDao createFromParcel(Parcel in) {
            return new PhotoItemDao(in);
        }

        @Override
        public PhotoItemDao[] newArray(int size) {
            return new PhotoItemDao[size];
        }
    };

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTopic() {
        return newsTopic;
    }

    public void setNewsTopic(String newsTopic) {
        this.newsTopic = newsTopic;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getNewsFilename() {
        return newsFilename;
    }

    public void setNewsFilename(String newsFilename) {
        this.newsFilename = newsFilename;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newsId);
        dest.writeString(newsTopic);
        dest.writeString(newsDetail);
        dest.writeString(newsFilename);
        dest.writeString(newsDate);
    }
}
