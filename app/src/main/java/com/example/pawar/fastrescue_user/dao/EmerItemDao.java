package com.example.pawar.fastrescue_user.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 04-Feb-17.
 */

public class EmerItemDao implements Parcelable {
    @SerializedName("noti_id")
    @Expose
    private String notiId;
    @SerializedName("noti_latitude")
    @Expose
    private String notiLatitude;
    @SerializedName("noti_longtitude")
    @Expose
    private String notiLongtitude;
    @SerializedName("noti_filename")
    @Expose
    private String notiFilename;
    @SerializedName("noti_event")
    @Expose
    private String notiEvent;
    @SerializedName("noti_detail")
    @Expose
    private String notiDetail;
    @SerializedName("pnoti_status")
    @Expose
    private String pnotiStatus;
    @SerializedName("noti_date")
    @Expose
    private String notiDate;
    @SerializedName("noti_status")
    @Expose
    private String notiStatus;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("server_id")
    @Expose
    private String serverId;
    @SerializedName("official_id1")
    @Expose
    private String officialId1;
    @SerializedName("time_id1")
    @Expose
    private String timeId1;
    @SerializedName("official_id2")
    @Expose
    private String officialId2;
    @SerializedName("time_id2")
    @Expose
    private String timeId2;
    @SerializedName("official_id3")
    @Expose
    private String officialId3;
    @SerializedName("time_id3")
    @Expose
    private String timeId3;
    @SerializedName("official_accept")
    @Expose
    private String officialAccept;

    protected EmerItemDao(Parcel in) {
        notiId = in.readString();
        notiLatitude = in.readString();
        notiLongtitude = in.readString();
        notiFilename = in.readString();
        notiEvent = in.readString();
        notiDetail = in.readString();
        pnotiStatus = in.readString();
        notiDate = in.readString();
        notiStatus = in.readString();
        userId = in.readString();
        serverId = in.readString();
        officialId1 = in.readString();
        timeId1 = in.readString();
        officialId2 = in.readString();
        timeId2 = in.readString();
        officialId3 = in.readString();
        timeId3 = in.readString();
        officialAccept = in.readString();
    }

    public static final Creator<EmerItemDao> CREATOR = new Creator<EmerItemDao>() {
        @Override
        public EmerItemDao createFromParcel(Parcel in) {
            return new EmerItemDao(in);
        }

        @Override
        public EmerItemDao[] newArray(int size) {
            return new EmerItemDao[size];
        }
    };

    public String getNotiId() {
        return notiId;
    }

    public void setNotiId(String notiId) {
        this.notiId = notiId;
    }

    public String getNotiLatitude() {
        return notiLatitude;
    }

    public void setNotiLatitude(String notiLatitude) {
        this.notiLatitude = notiLatitude;
    }

    public String getNotiLongtitude() {
        return notiLongtitude;
    }

    public void setNotiLongtitude(String notiLongtitude) {
        this.notiLongtitude = notiLongtitude;
    }

    public String getNotiFilename() {
        return notiFilename;
    }

    public void setNotiFilename(String notiFilename) {
        this.notiFilename = notiFilename;
    }

    public String getNotiEvent() {
        return notiEvent;
    }

    public void setNotiEvent(String notiEvent) {
        this.notiEvent = notiEvent;
    }

    public String getNotiDetail() {
        return notiDetail;
    }

    public void setNotiDetail(String notiDetail) {
        this.notiDetail = notiDetail;
    }

    public String getPnotiStatus() {
        return pnotiStatus;
    }

    public void setPnotiStatus(String pnotiStatus) {
        this.pnotiStatus = pnotiStatus;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiStatus() {
        return notiStatus;
    }

    public void setNotiStatus(String notiStatus) {
        this.notiStatus = notiStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getOfficialId1() {
        return officialId1;
    }

    public void setOfficialId1(String officialId1) {
        this.officialId1 = officialId1;
    }

    public String getTimeId1() {
        return timeId1;
    }

    public void setTimeId1(String timeId1) {
        this.timeId1 = timeId1;
    }

    public String getOfficialId2() {
        return officialId2;
    }

    public void setOfficialId2(String officialId2) {
        this.officialId2 = officialId2;
    }

    public String getTimeId2() {
        return timeId2;
    }

    public void setTimeId2(String timeId2) {
        this.timeId2 = timeId2;
    }

    public String getOfficialId3() {
        return officialId3;
    }

    public void setOfficialId3(String officialId3) {
        this.officialId3 = officialId3;
    }

    public String getTimeId3() {
        return timeId3;
    }

    public void setTimeId3(String timeId3) {
        this.timeId3 = timeId3;
    }

    public String getOfficialAccept() {
        return officialAccept;
    }

    public void setOfficialAccept(String officialAccept) {
        this.officialAccept = officialAccept;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notiId);
        dest.writeString(notiLatitude);
        dest.writeString(notiLongtitude);
        dest.writeString(notiFilename);
        dest.writeString(notiEvent);
        dest.writeString(notiDetail);
        dest.writeString(pnotiStatus);
        dest.writeString(notiDate);
        dest.writeString(notiStatus);
        dest.writeString(userId);
        dest.writeString(serverId);
        dest.writeString(officialId1);
        dest.writeString(timeId1);
        dest.writeString(officialId2);
        dest.writeString(timeId2);
        dest.writeString(officialId3);
        dest.writeString(timeId3);
        dest.writeString(officialAccept);
    }
}

