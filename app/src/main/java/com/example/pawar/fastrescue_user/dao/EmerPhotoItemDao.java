package com.example.pawar.fastrescue_user.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 04-Feb-17.
 */

public class EmerPhotoItemDao implements Parcelable {

    @SerializedName("file_id")
    @Expose
    private String fileId;
    @SerializedName("noti_filename")
    @Expose
    private String notiFilename;
    @SerializedName("noti_id")
    @Expose
    private String notiId;

    protected EmerPhotoItemDao(Parcel in) {
        fileId = in.readString();
        notiFilename = in.readString();
        notiId = in.readString();
    }

    public static final Creator<EmerPhotoItemDao> CREATOR = new Creator<EmerPhotoItemDao>() {
        @Override
        public EmerPhotoItemDao createFromParcel(Parcel in) {
            return new EmerPhotoItemDao(in);
        }

        @Override
        public EmerPhotoItemDao[] newArray(int size) {
            return new EmerPhotoItemDao[size];
        }
    };

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getNotiFilename() {
        return notiFilename;
    }

    public void setNotiFilename(String notiFilename) {
        this.notiFilename = notiFilename;
    }

    public String getNotiId() {
        return notiId;
    }

    public void setNotiId(String notiId) {
        this.notiId = notiId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileId);
        dest.writeString(notiFilename);
        dest.writeString(notiId);
    }
}

