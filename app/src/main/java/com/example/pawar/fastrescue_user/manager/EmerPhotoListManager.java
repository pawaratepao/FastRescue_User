package com.example.pawar.fastrescue_user.manager;

import android.content.Context;

import com.example.pawar.fastrescue_user.dao.EmerPhotoCollectionDao;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class EmerPhotoListManager {

    private Context mContext;
    private static EmerPhotoCollectionDao dao;

    public EmerPhotoListManager() {
        mContext = Contextor.getInstance().getContext();

    }

    public static EmerPhotoCollectionDao getDao() {
        return dao;
    }

    public void setDao(EmerPhotoCollectionDao dao) {
        this.dao = dao;
    }


}
