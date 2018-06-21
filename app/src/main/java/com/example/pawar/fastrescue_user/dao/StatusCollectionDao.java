package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pawar on 01-Mar-17.
 */

public class StatusCollectionDao {

    @SerializedName("SUCCESS")
    @Expose
    private Boolean sUCCESS;
    @SerializedName("data")
    @Expose
    private List<StatusItemDao> data = null;

    public Boolean getSUCCESS() {
        return sUCCESS;
    }

    public void setSUCCESS(Boolean sUCCESS) {
        this.sUCCESS = sUCCESS;
    }

    public List<StatusItemDao> getData() {
        return data;
    }

    public void setData(List<StatusItemDao> data) {
        this.data = data;
    }

}

