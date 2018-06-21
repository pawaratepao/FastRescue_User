package com.example.pawar.fastrescue_user.manager.http;



import com.example.pawar.fastrescue_user.dao.EmerCollectionDao;
import com.example.pawar.fastrescue_user.dao.EmerPhotoCollectionDao;
import com.example.pawar.fastrescue_user.dao.OfficialCollectionDao;
import com.example.pawar.fastrescue_user.dao.PhotoCollectionDao;
import com.example.pawar.fastrescue_user.dao.ServerResponse;
import com.example.pawar.fastrescue_user.dao.StatusCollectionDao;
import com.example.pawar.fastrescue_user.dao.UserCollectionDao;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by pawar on 04-Feb-17.
 */

public interface ApiService {
    @POST("android/news.php")
    Call<PhotoCollectionDao> loadPhotoList();

    @FormUrlEncoded
    @POST("android/multi_pic.php")
    Call<EmerPhotoCollectionDao> loadEmerPhotoList(@Field("noti_id") String noti_id);

    @FormUrlEncoded
    @POST("android/showstatus.php")
    Call<StatusCollectionDao> loadStatusList(@Field("noti_user") String username);

    @FormUrlEncoded
    @POST("img/cancelemer.php")
    Call<ServerResponse> RemoveFailSend(@Field("noti_filename") String noti_id);

    @FormUrlEncoded
    @POST("android/detail_user1.php")
    Call<UserCollectionDao> loadUserData(@Field("user_username") String username);

    @FormUrlEncoded
    @POST("android/list.php")
    Call<EmerCollectionDao> loadEmerData(@Field("official_id") String username);

    @FormUrlEncoded
    @POST("android/detail_official1.php")
    Call<OfficialCollectionDao> loadOfficialData(@Field("user_username") String username);

    @Multipart
    @POST("img/upload.php")
    Call<ServerResponse> updateImageProfile(@Part MultipartBody.Part file,
                                            @Part("file") RequestBody name);

    @FormUrlEncoded
    @POST("android/detail_official.php")
    Call<OfficialCollectionDao> loadOfficialDataid(@Field("user_id") String username);

    @FormUrlEncoded
    @POST("android/detail_user.php")
    Call<UserCollectionDao> loadUserDataid(@Field("user_id") String username);



}
