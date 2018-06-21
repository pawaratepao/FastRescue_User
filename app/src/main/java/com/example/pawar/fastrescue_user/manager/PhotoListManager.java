package com.example.pawar.fastrescue_user.manager;

import android.content.Context;

import com.example.pawar.fastrescue_user.dao.PhotoCollectionDao;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoListManager {

    private Context mContext;
    private static PhotoCollectionDao dao;

    public PhotoListManager() {
        mContext = Contextor.getInstance().getContext();

    }

    public static PhotoCollectionDao getDao() {
        return dao;
    }

    public void setDao(PhotoCollectionDao dao) {
        this.dao = dao;
    }


}
