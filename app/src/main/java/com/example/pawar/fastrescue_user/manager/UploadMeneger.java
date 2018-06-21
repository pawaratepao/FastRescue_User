package com.example.pawar.fastrescue_user.manager;

import android.content.Context;

import com.example.pawar.fastrescue_user.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class UploadMeneger {

    private static UploadMeneger instance;

    public static UploadMeneger getInstance() {
        if (instance == null)
            instance = new UploadMeneger();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private UploadMeneger() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pohtecktung.welovepc.com/newproject/img/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
