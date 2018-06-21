package com.example.pawar.fastrescue_user.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.OfficialCollectionDao;
import com.example.pawar.fastrescue_user.dao.UserCollectionDao;
import com.example.pawar.fastrescue_user.fragment.MemDetailEditFragment;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class EditDetailActivity extends AppCompatActivity implements MemDetailEditFragment.MemDetailEditFragmentListener {
    Toolbar toolbar;
    String  udusername,udfirstname, udlastname, udtel, udteletc, udpersonalid, udbirthday, udaddress, uddisease,
            udbloodgroup, udallergic, udsecurity;
    private static final String URL_UPDATE_USER = "http://pohtecktung.welovepc.com/newproject/android/update_user.php";
    private static final String URL_UPDATE_OFFICIAL = "http://pohtecktung.welovepc.com/newproject/android/update_official.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MemDetailEditFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("แก้ไขข้อมูล");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void EditCommitClicked(String username,String firstname, String lastname, String tel, String teletc, String personalid, String birthday,
                                  String address, String disease, String bloodgroup, String allergic, String security) {


        udusername = username;
        udfirstname = firstname;
        udlastname = lastname;
        udtel = tel;
        udteletc = teletc;
        udpersonalid = personalid;
        udbirthday = birthday;
        udaddress = address;
        uddisease = disease;
        udbloodgroup = bloodgroup;
        udallergic = allergic;
        udsecurity = security;

        checkStatus();



    }

    public void checkStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String status = sharedPreferences.getString("status", null);
        if (status.equals("user")) {
            updateUser();


        } else {
            updateOfficial();

        }

    }

    public void updateUser() {
        if (!udusername.isEmpty() && !udlastname.isEmpty()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE_USER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    Toast.makeText(EditDetailActivity.this, "แก้ไขข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    getUsernameData(udusername);
                    //Intent intent = new Intent(EditDetailActivity.this, MainActivity.class);
                    //startActivity(intent);
                    //finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString());
                    Toast.makeText(EditDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditDetailActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("user_username",udusername);
                    params.put("user_firstname", udfirstname);
                    params.put("user_lastname", udlastname);
                    params.put("user_tel", udtel);
                    params.put("user_teletc", udteletc);
                    params.put("user_personalid", udpersonalid);
                    params.put("user_birthday", udbirthday);
                    params.put("user_address", udaddress);
                    params.put("user_disease", uddisease);
                    params.put("user_bloodgroup", udbloodgroup);
                    params.put("user_allergic", udallergic);
                    params.put("user_security", udsecurity);


                    return params;
                }
            };
            requestQueue.add(request);
        }
    }

    public void updateOfficial() {
        if (!udfirstname.isEmpty() && !udlastname.isEmpty()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL_UPDATE_OFFICIAL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    Toast.makeText(EditDetailActivity.this, "แก้ไขข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    getOfficialData(udusername);
                    //Intent intent = new Intent(EditDetailActivity.this, MainActivity.class);
                    //startActivity(intent);
                    //finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString());
                    Toast.makeText(EditDetailActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditDetailActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("official_username",udusername);
                    params.put("official_firstname", udfirstname);
                    params.put("official_lastname", udlastname);
                    params.put("official_tel", udtel);
                    params.put("official_teletc", udteletc);
                    params.put("official_personalid", udpersonalid);
                    params.put("official_birthday", udbirthday);
                    params.put("official_address", udaddress);
                    params.put("official_disease", uddisease);
                    params.put("official_bloodgroup", udbloodgroup);
                    params.put("official_allergic", udallergic);
                    params.put("official_security", udsecurity);


                    return params;
                }
            };
            requestQueue.add(request);
        }

    }

    public void getUsernameData(final String udusername) {
        Call<UserCollectionDao> call = HttpMeneger.getInstance().getService().loadUserData(udusername);
        call.enqueue(new Callback<UserCollectionDao>() {
                         @Override
                         public void onResponse(Call<UserCollectionDao> call, retrofit2.Response<UserCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 UserCollectionDao userdao = response.body();
                                 crateUserDataFile(userdao);


                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                             "ABC" + response.errorBody().string(),
                                             Toast.LENGTH_LONG).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<UserCollectionDao> call, Throwable t) {
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     "123" + t.toString(),
                                     Toast.LENGTH_LONG).show();
                         }

                     }

        );
    }

    public void getOfficialData(final String udusername) {
        Call<OfficialCollectionDao> call = HttpMeneger.getInstance().getService().loadOfficialData(udusername);
        call.enqueue(new Callback<OfficialCollectionDao>() {
                         @Override
                         public void onResponse(Call<OfficialCollectionDao> call, retrofit2.Response<OfficialCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 OfficialCollectionDao officialdao = response.body();
                                 crateOfficialDataFile(officialdao);


                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                             "ABC" + response.errorBody().string(),
                                             Toast.LENGTH_LONG).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<OfficialCollectionDao> call, Throwable t) {
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     "123" + t.toString(),
                                     Toast.LENGTH_LONG).show();
                         }

                     }

        );
    }

    public void crateUserDataFile(UserCollectionDao dao) {
        String userUsername = dao.getData().get(0).getUserUsername();
        String userFirstname = dao.getData().get(0).getUserFirstname();
        String userLastname = dao.getData().get(0).getUserLastname();
        String userSex = dao.getData().get(0).getUserSex();
        String userTel = dao.getData().get(0).getUserTel();
        String userTeletc = dao.getData().get(0).getUserTeletc();
        String userPersonalid = dao.getData().get(0).getUserPersonalid();
        String userBirthday = dao.getData().get(0).getUserBirthday();
        String userAddress = dao.getData().get(0).getUserAddress();
        String userDisease = dao.getData().get(0).getUserDisease();
        String userBloodgroup = dao.getData().get(0).getUserBloodgroup();
        String userAllergic = dao.getData().get(0).getUserAllergic();
        String userSecurity = dao.getData().get(0).getUserSecurity();
        String status = "user";
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userUsername);
        editor.putString("firstname", userFirstname);
        editor.putString("lastname", userLastname);
        editor.putString("sex", userSex);
        editor.putString("tel", userTel);
        editor.putString("teletc", userTeletc);
        editor.putString("birthday", userBirthday);
        editor.putString("address", userAddress);
        editor.putString("disease", userDisease);
        editor.putString("personalid", userPersonalid);
        editor.putString("bloodgroup", userBloodgroup);
        editor.putString("allergic", userAllergic);
        editor.putString("security", userSecurity);
        editor.putString("status", status);
        editor.commit();
        finish();
    }

    public void crateOfficialDataFile(OfficialCollectionDao dao) {
        String offcialUsername = dao.getData().get(0).getOfficialUsername();
        String offcialFirstname = dao.getData().get(0).getOfficialFirstname();
        String offcialLastname = dao.getData().get(0).getOfficialLastname();
        String offcialSex = dao.getData().get(0).getOfficialSex();
        String offcialTel = dao.getData().get(0).getOfficialTel();
        String offcialTeletc = dao.getData().get(0).getOfficialTeletc();
        String offcialPersonalid = dao.getData().get(0).getOfficialPersonalid();
        String offcialBirthday = dao.getData().get(0).getOfficialBirthday();
        String offcialAddress = dao.getData().get(0).getOfficialAddress();
        String offcialDisease = dao.getData().get(0).getOfficialDisease();
        String offcialBloodgroup = dao.getData().get(0).getOfficialBloodgroup();
        String offcialAllergic = dao.getData().get(0).getOfficialAllergic();
        String offcialSecurity = dao.getData().get(0).getOfficialSecurity();
        String status = "official";
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", offcialUsername);
        editor.putString("firstname", offcialFirstname);
        editor.putString("lastname", offcialLastname);
        editor.putString("sex", offcialSex);
        editor.putString("tel", offcialTel);
        editor.putString("teletc", offcialTeletc);
        editor.putString("birthday", offcialBirthday);
        editor.putString("address", offcialAddress);
        editor.putString("disease", offcialDisease);
        editor.putString("personalid", offcialPersonalid);
        editor.putString("bloodgroup", offcialBloodgroup);
        editor.putString("allergic", offcialAllergic);
        editor.putString("security", offcialSecurity);
        editor.putString("status", status);
        editor.commit();
        finish();


    }
}


