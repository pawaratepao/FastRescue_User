package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pawar on 28-Feb-17.
 */

public class UserItemDao {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_username")
    @Expose
    private String userUsername;
    @SerializedName("user_password")
    @Expose
    private String userPassword;
    @SerializedName("user_firstname")
    @Expose
    private String userFirstname;
    @SerializedName("user_lastname")
    @Expose
    private String userLastname;
    @SerializedName("user_sex")
    @Expose
    private String userSex;
    @SerializedName("user_tel")
    @Expose
    private String userTel;
    @SerializedName("user_teletc")
    @Expose
    private String userTeletc;
    @SerializedName("user_personalid")
    @Expose
    private String userPersonalid;
    @SerializedName("user_birthday")
    @Expose
    private String userBirthday;
    @SerializedName("user_address")
    @Expose
    private String userAddress;
    @SerializedName("user_disease")
    @Expose
    private String userDisease;
    @SerializedName("user_bloodgroup")
    @Expose
    private String userBloodgroup;
    @SerializedName("user_allergic")
    @Expose
    private String userAllergic;
    @SerializedName("user_security")
    @Expose
    private String userSecurity;
    @SerializedName("user_filename")
    @Expose
    private String userFilename;
    @SerializedName("user_status")
    @Expose
    private String userStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserTeletc() {
        return userTeletc;
    }

    public void setUserTeletc(String userTeletc) {
        this.userTeletc = userTeletc;
    }

    public String getUserPersonalid() {
        return userPersonalid;
    }

    public void setUserPersonalid(String userPersonalid) {
        this.userPersonalid = userPersonalid;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserDisease() {
        return userDisease;
    }

    public void setUserDisease(String userDisease) {
        this.userDisease = userDisease;
    }

    public String getUserBloodgroup() {
        return userBloodgroup;
    }

    public void setUserBloodgroup(String userBloodgroup) {
        this.userBloodgroup = userBloodgroup;
    }

    public String getUserAllergic() {
        return userAllergic;
    }

    public void setUserAllergic(String userAllergic) {
        this.userAllergic = userAllergic;
    }

    public String getUserSecurity() {
        return userSecurity;
    }

    public void setUserSecurity(String userSecurity) {
        this.userSecurity = userSecurity;
    }

    public String getUserFilename() {
        return userFilename;
    }

    public void setUserFilename(String userFilename) {
        this.userFilename = userFilename;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

}

