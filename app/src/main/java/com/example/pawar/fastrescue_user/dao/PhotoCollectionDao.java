package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pawar on 24-Feb-17.
 */

public class PhotoCollectionDao {
    @SerializedName("data")
    @Expose
    private List<PhotoItemDao> data = null;

    public List<PhotoItemDao> getData() {
        return data;
    }

    public void setData(List<PhotoItemDao> data) {
        this.data = data;
    }
}
