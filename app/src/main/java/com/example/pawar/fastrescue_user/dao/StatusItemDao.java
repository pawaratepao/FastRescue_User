package com.example.pawar.fastrescue_user.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 28-Feb-17.
 */

public class StatusItemDao implements Parcelable {


    @SerializedName("noti_id")
    @Expose
    private String notiId;
    @SerializedName("noti_latitude")
    @Expose
    private String notiLatitude;
    @SerializedName("noti_longitude")
    @Expose
    private String notiLongitude;
    @SerializedName("noti_event")
    @Expose
    private String notiEvent;
    @SerializedName("noti_detail")
    @Expose
    private String notiDetail;
    @SerializedName("noti_pstatus")
    @Expose
    private String notiPstatus;
    @SerializedName("noti_date")
    @Expose
    private String notiDate;
    @SerializedName("noti_user")
    @Expose
    private String notiUser;
    @SerializedName("noti_official")
    @Expose
    private String notiOfficial;
    @SerializedName("monitor_id")
    @Expose
    private String monitorId;
    @SerializedName("noti_status")
    @Expose
    private String notiStatus;
    @SerializedName("file_id")
    @Expose
    private String fileId;
    @SerializedName("noti_filename")
    @Expose
    private String notiFilename;
    @SerializedName("accept_id")
    @Expose
    private String acceptId;
    @SerializedName("official_id")
    @Expose
    private String officialId;
    @SerializedName("official_time")
    @Expose
    private String officialTime;
    @SerializedName("accept_status")
    @Expose
    private String acceptStatus;
    @SerializedName("official_username")
    @Expose
    private String officialUsername;
    @SerializedName("official_password")
    @Expose
    private String officialPassword;
    @SerializedName("official_firstname")
    @Expose
    private String officialFirstname;
    @SerializedName("official_lastname")
    @Expose
    private String officialLastname;
    @SerializedName("official_sex")
    @Expose
    private String officialSex;
    @SerializedName("official_tel")
    @Expose
    private String officialTel;
    @SerializedName("official_teletc")
    @Expose
    private String officialTeletc;
    @SerializedName("official_personalid")
    @Expose
    private String officialPersonalid;
    @SerializedName("official_birthday")
    @Expose
    private String officialBirthday;
    @SerializedName("official_address")
    @Expose
    private String officialAddress;
    @SerializedName("official_disease")
    @Expose
    private String officialDisease;
    @SerializedName("official_bloodgroup")
    @Expose
    private String officialBloodgroup;
    @SerializedName("official_allergic")
    @Expose
    private String officialAllergic;
    @SerializedName("official_security")
    @Expose
    private String officialSecurity;
    @SerializedName("official_latitude")
    @Expose
    private String officialLatitude;
    @SerializedName("official_longitude")
    @Expose
    private String officialLongitude;
    @SerializedName("official_online")
    @Expose
    private String officialOnline;
    @SerializedName("official_token")
    @Expose
    private String officialToken;
    @SerializedName("official_team")
    @Expose
    private String officialTeam;

    protected StatusItemDao(Parcel in) {
        notiId = in.readString();
        notiLatitude = in.readString();
        notiLongitude = in.readString();
        notiEvent = in.readString();
        notiDetail = in.readString();
        notiPstatus = in.readString();
        notiDate = in.readString();
        notiUser = in.readString();
        notiOfficial = in.readString();
        monitorId = in.readString();
        notiStatus = in.readString();
        fileId = in.readString();
        notiFilename = in.readString();
        acceptId = in.readString();
        officialId = in.readString();
        officialTime = in.readString();
        acceptStatus = in.readString();
        officialUsername = in.readString();
        officialPassword = in.readString();
        officialFirstname = in.readString();
        officialLastname = in.readString();
        officialSex = in.readString();
        officialTel = in.readString();
        officialTeletc = in.readString();
        officialPersonalid = in.readString();
        officialBirthday = in.readString();
        officialAddress = in.readString();
        officialDisease = in.readString();
        officialBloodgroup = in.readString();
        officialAllergic = in.readString();
        officialSecurity = in.readString();
        officialLatitude = in.readString();
        officialLongitude = in.readString();
        officialOnline = in.readString();
        officialToken = in.readString();
        officialTeam = in.readString();
    }

    public static final Creator<StatusItemDao> CREATOR = new Creator<StatusItemDao>() {
        @Override
        public StatusItemDao createFromParcel(Parcel in) {
            return new StatusItemDao(in);
        }

        @Override
        public StatusItemDao[] newArray(int size) {
            return new StatusItemDao[size];
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

    public String getNotiLongitude() {
        return notiLongitude;
    }

    public void setNotiLongitude(String notiLongitude) {
        this.notiLongitude = notiLongitude;
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

    public String getNotiPstatus() {
        return notiPstatus;
    }

    public void setNotiPstatus(String notiPstatus) {
        this.notiPstatus = notiPstatus;
    }

    public String getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(String notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiUser() {
        return notiUser;
    }

    public void setNotiUser(String notiUser) {
        this.notiUser = notiUser;
    }

    public String getNotiOfficial() {
        return notiOfficial;
    }

    public void setNotiOfficial(String notiOfficial) {
        this.notiOfficial = notiOfficial;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) {
        this.monitorId = monitorId;
    }

    public String getNotiStatus() {
        return notiStatus;
    }

    public void setNotiStatus(String notiStatus) {
        this.notiStatus = notiStatus;
    }

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

    public String getAcceptId() {
        return acceptId;
    }

    public void setAcceptId(String acceptId) {
        this.acceptId = acceptId;
    }

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getOfficialTime() {
        return officialTime;
    }

    public void setOfficialTime(String officialTime) {
        this.officialTime = officialTime;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    public String getOfficialUsername() {
        return officialUsername;
    }

    public void setOfficialUsername(String officialUsername) {
        this.officialUsername = officialUsername;
    }

    public String getOfficialPassword() {
        return officialPassword;
    }

    public void setOfficialPassword(String officialPassword) {
        this.officialPassword = officialPassword;
    }

    public String getOfficialFirstname() {
        return officialFirstname;
    }

    public void setOfficialFirstname(String officialFirstname) {
        this.officialFirstname = officialFirstname;
    }

    public String getOfficialLastname() {
        return officialLastname;
    }

    public void setOfficialLastname(String officialLastname) {
        this.officialLastname = officialLastname;
    }

    public String getOfficialSex() {
        return officialSex;
    }

    public void setOfficialSex(String officialSex) {
        this.officialSex = officialSex;
    }

    public String getOfficialTel() {
        return officialTel;
    }

    public void setOfficialTel(String officialTel) {
        this.officialTel = officialTel;
    }

    public String getOfficialTeletc() {
        return officialTeletc;
    }

    public void setOfficialTeletc(String officialTeletc) {
        this.officialTeletc = officialTeletc;
    }

    public String getOfficialPersonalid() {
        return officialPersonalid;
    }

    public void setOfficialPersonalid(String officialPersonalid) {
        this.officialPersonalid = officialPersonalid;
    }

    public String getOfficialBirthday() {
        return officialBirthday;
    }

    public void setOfficialBirthday(String officialBirthday) {
        this.officialBirthday = officialBirthday;
    }

    public String getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(String officialAddress) {
        this.officialAddress = officialAddress;
    }

    public String getOfficialDisease() {
        return officialDisease;
    }

    public void setOfficialDisease(String officialDisease) {
        this.officialDisease = officialDisease;
    }

    public String getOfficialBloodgroup() {
        return officialBloodgroup;
    }

    public void setOfficialBloodgroup(String officialBloodgroup) {
        this.officialBloodgroup = officialBloodgroup;
    }

    public String getOfficialAllergic() {
        return officialAllergic;
    }

    public void setOfficialAllergic(String officialAllergic) {
        this.officialAllergic = officialAllergic;
    }

    public String getOfficialSecurity() {
        return officialSecurity;
    }

    public void setOfficialSecurity(String officialSecurity) {
        this.officialSecurity = officialSecurity;
    }

    public String getOfficialLatitude() {
        return officialLatitude;
    }

    public void setOfficialLatitude(String officialLatitude) {
        this.officialLatitude = officialLatitude;
    }

    public String getOfficialLongitude() {
        return officialLongitude;
    }

    public void setOfficialLongitude(String officialLongitude) {
        this.officialLongitude = officialLongitude;
    }

    public String getOfficialOnline() {
        return officialOnline;
    }

    public void setOfficialOnline(String officialOnline) {
        this.officialOnline = officialOnline;
    }

    public String getOfficialToken() {
        return officialToken;
    }

    public void setOfficialToken(String officialToken) {
        this.officialToken = officialToken;
    }

    public String getOfficialTeam() {
        return officialTeam;
    }

    public void setOfficialTeam(String officialTeam) {
        this.officialTeam = officialTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notiId);
        dest.writeString(notiLatitude);
        dest.writeString(notiLongitude);
        dest.writeString(notiEvent);
        dest.writeString(notiDetail);
        dest.writeString(notiPstatus);
        dest.writeString(notiDate);
        dest.writeString(notiUser);
        dest.writeString(notiOfficial);
        dest.writeString(monitorId);
        dest.writeString(notiStatus);
        dest.writeString(fileId);
        dest.writeString(notiFilename);
        dest.writeString(acceptId);
        dest.writeString(officialId);
        dest.writeString(officialTime);
        dest.writeString(acceptStatus);
        dest.writeString(officialUsername);
        dest.writeString(officialPassword);
        dest.writeString(officialFirstname);
        dest.writeString(officialLastname);
        dest.writeString(officialSex);
        dest.writeString(officialTel);
        dest.writeString(officialTeletc);
        dest.writeString(officialPersonalid);
        dest.writeString(officialBirthday);
        dest.writeString(officialAddress);
        dest.writeString(officialDisease);
        dest.writeString(officialBloodgroup);
        dest.writeString(officialAllergic);
        dest.writeString(officialSecurity);
        dest.writeString(officialLatitude);
        dest.writeString(officialLongitude);
        dest.writeString(officialOnline);
        dest.writeString(officialToken);
        dest.writeString(officialTeam);
    }
}

