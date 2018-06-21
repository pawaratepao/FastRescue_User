package com.example.pawar.fastrescue_user.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.UserCollectionDao;
import com.example.pawar.fastrescue_user.fragment.MainFragment;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    public static final String USER_LOGIN_URL = "http://pohtecktung.welovepc.com/newproject/android/login_user.php";
    public static final String OFFICIAL_LOGIN_URL = "http://pohtecktung.welovepc.com/newproject/android/login_official.php";


    private String username;
    private String password;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onUserLoginClicked(String uusername, String upassword) {

        progress = ProgressDialog.show(this, "โปรดรอสักครู่",
                "Loading...", true);

        if (!uusername.isEmpty() && !upassword.isEmpty()) {
            username = "user_"+uusername;
            password = upassword;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equals(username)) {
                                getUsernameData(username);
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            } else {
                                progress.dismiss();
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("user_username", username);
                    map.put("user_password", password);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(MainActivity.this, "กรุณาใส Username และ Password ให้ครบถ้วน", Toast.LENGTH_SHORT).show();
            progress.dismiss();


        }


    }

    public void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        String checkUsername = sharedPreferences.getString("username", null);
        if (checkUsername != null) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
    }



    @Override
    public void onRegisterClicked() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    public void getUsernameData(final String username) {
        Call<UserCollectionDao> call = HttpMeneger.getInstance().getService().loadUserData(username);
        call.enqueue(new Callback<UserCollectionDao>() {
                         @Override
                         public void onResponse(Call<UserCollectionDao> call, retrofit2.Response<UserCollectionDao> response) {
                             if (response.isSuccessful()) {
                                 UserCollectionDao userdao = response.body();
                                 crateUserDataFile(userdao);
                                 //Toast.makeText(Contextor.getInstance().getContext(),
                                 // userdao.getData().get(0).getUserFirstname() + " " + userdao.getData().get(0).getUserLastname(),
                                 //Toast.LENGTH_SHORT).show();

                             } else {
                                 try {
                                     Toast.makeText(Contextor.getInstance().getContext(),
                                             response.errorBody().string(),
                                             Toast.LENGTH_LONG).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }

                             }

                         }

                         @Override
                         public void onFailure(Call<UserCollectionDao> call, Throwable t) {
                             Toast.makeText(Contextor.getInstance().getContext(),
                                     t.toString(),
                                     Toast.LENGTH_LONG).show();
                         }

                     }

        );
    }



    public void crateUserDataFile(UserCollectionDao dao) {
        String userId = dao.getData().get(0).getUserId();
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
        editor.putString("id", userId);
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
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        progress.dismiss();
    }


}