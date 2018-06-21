package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pawar on 01-Mar-17.
 */

public class OfficialCollectionDao {

    @SerializedName("data")
    @Expose
    private List<OfficialItemDao> data = null;

    public List<OfficialItemDao> getData() {
        return data;
    }

    public void setData(List<OfficialItemDao> data) {
        this.data = data;
    }
}
