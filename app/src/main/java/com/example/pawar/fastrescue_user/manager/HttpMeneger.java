package com.example.pawar.fastrescue_user.manager;

import android.content.Context;

import com.example.pawar.fastrescue_user.manager.http.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class HttpMeneger {

    private static HttpMeneger instance;

    public static HttpMeneger getInstance() {
        if (instance == null)
            instance = new HttpMeneger();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpMeneger() {
        mContext = Contextor.getInstance().getContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pohtecktung.welovepc.com/newproject/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
