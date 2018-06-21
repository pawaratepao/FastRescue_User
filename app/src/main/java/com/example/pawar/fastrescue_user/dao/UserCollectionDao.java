package com.example.pawar.fastrescue_user.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pawar on 01-Mar-17.
 */

public class UserCollectionDao {
    @SerializedName("data")
    @Expose
    private List<UserItemDao> data = null;

    public List<UserItemDao> getData() {
        return data;
    }

    public void setData(List<UserItemDao> data) {
        this.data = data;
    }

}
