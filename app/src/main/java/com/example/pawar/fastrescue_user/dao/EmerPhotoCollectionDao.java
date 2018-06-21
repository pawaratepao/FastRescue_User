package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pawar on 24-Feb-17.
 */

public class EmerPhotoCollectionDao {
    @SerializedName("SUCCESS")
    @Expose
    private Boolean sUCCESS;
    @SerializedName("data")
    @Expose
    private List<EmerPhotoItemDao> data = null;

    public Boolean getSUCCESS() {
        return sUCCESS;
    }

    public void setSUCCESS(Boolean sUCCESS) {
        this.sUCCESS = sUCCESS;
    }

    public List<EmerPhotoItemDao> getData() {
        return data;
    }

    public void setData(List<EmerPhotoItemDao> data) {
        this.data = data;
    }

}
