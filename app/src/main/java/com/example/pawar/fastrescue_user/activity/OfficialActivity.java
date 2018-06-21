package com.example.pawar.fastrescue_user.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
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
import com.example.pawar.fastrescue_user.fragment.HomeOfficialFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class OfficialActivity extends AppCompatActivity implements HomeOfficialFragment.HomeOffFragmentListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    final String online_URL = "http://pohtecktung.welovepc.com/finalproject/android/online.php";
    final String offline_URL = "http://pohtecktung.welovepc.com/finalproject/android/offline.php";
    final String check_URL = "http://pohtecktung.welovepc.com/finalproject/android/returnStatus.php";
    Toolbar toolbar;
    Switch mySwitch;
    TextView tv_on_status;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    Double latitude, longitude;
    String username, lat, lng, token;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, HomeOfficialFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("หน้าหลัก");

        progress = ProgressDialog.show(this, "โปรดรอสักครู่",
                "Loading...", true);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        toastToken();

        tv_on_status = (TextView) findViewById(R.id.tv_on_status);

        mySwitch = (Switch) findViewById(R.id.mySwitch);
        checkStatus();
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkEmer();


                } else {
                    tv_on_status.setText("OFFLINE");
                    sendDataOffline();

                }
            }
        });
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        checkStatus();
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        checkStatus();
        super.onResume();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            super.onStop();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        sendDataOffline();
        finish();
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    public void OffNewsClicked() {
        Intent intent = new Intent(OfficialActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    @Override
    public void OffDetailClicked() {
        Intent intent = new Intent(OfficialActivity.this, DetailActivity.class);
        startActivity(intent);


    }

    @Override
    public void OffStatusClicked() {
        Intent intent = new Intent(OfficialActivity.this, StatusActivity.class);
        startActivity(intent);

    }

    @Override
    public void OffEmergencyClicked() {
        Intent intent = new Intent(OfficialActivity.this, EmergencyActivity.class);
        startActivity(intent);

    }

    @Override
    public void OffReceiveClicked() {
        toastToken();
        SharedPreferences sp = getSharedPreferences("CHECK_NOTI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count_noti", 0);
        editor.commit();
        Intent intent = new Intent(OfficialActivity.this, ReciveEmerActivity.class);
        startActivity(intent);
    }

    @Override
    public void OffReEmerClicked() {
        SharedPreferences sp = getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        String check = sp.getString("noti_filename", null);
        if (check == null) {
            Toast.makeText(this, "ไม่มีเหตุที่รับไว้", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(OfficialActivity.this, TabRecieveActivity.class);
            startActivity(intent);
        }

    }


    public void toastToken() {
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN = ", "" + token);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        final LocationManager managerL = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (!managerL.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            displayPromptForEnablingGPS(this);
        }

//what you want to do
        else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                if (latitude != null || longitude != null) {
                    latitude = null;
                    longitude = null;
                }
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
                lat = latitude.toString();
                lng = longitude.toString();


            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public static void displayPromptForEnablingGPS(final Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "กรุณาเปิด GPS เพื่อรับตำแหน่งของท่าน";

        builder.setMessage(message)
                .setPositiveButton("ตกลง",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        });

        builder.create().show();
    }


    public void checkStatus() {


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, check_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);

                //Toast.makeText(OfficialActivity.this, response, Toast.LENGTH_SHORT).show();
                if (response.equals("online")) {
                    mySwitch.setChecked(true);
                    tv_on_status.setText("ONLINE");
                    progress.dismiss();
                } else {
                    mySwitch.setChecked(false);
                    tv_on_status.setText("OFFLINE");
                    progress.dismiss();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                mySwitch.setChecked(false);
                Toast.makeText(OfficialActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(OfficialActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("official_username", username);


                return params;
            }
        };
        requestQueue.add(request);
    }


    public void sendDataOnline() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, online_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                Toast.makeText(OfficialActivity.this, "พร้อมที่จะรับเหตุแล้ว", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("PREF_Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lat", lat);
                editor.putString("lng", lng);
                editor.commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                mySwitch.setChecked(false);
                Toast.makeText(OfficialActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(OfficialActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("official_username", username);
                params.put("official_latitude", lat);
                params.put("official_longtitude", lng);
                params.put("official_token", token);
                params.put("official_online", "online");


                return params;
            }
        };
        requestQueue.add(request);
    }

    public void sendDataOffline() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, offline_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("onResponse", response);
                Toast.makeText(OfficialActivity.this, "ปิดการรับเหตุ", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onError", error.toString());
                mySwitch.setChecked(true);
                Toast.makeText(OfficialActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(OfficialActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("official_username", username);
                params.put("official_online", "offline");


                return params;
            }
        };
        requestQueue.add(request);
    }


    public void checkEmer() {
        SharedPreferences sp = getSharedPreferences("RECEIVE_EMER", Context.MODE_PRIVATE);
        boolean chackfile = sp.getBoolean("recieve", false);
        if (chackfile != false) {
            mySwitch.setChecked(false);
            Toast.makeText(this, "มีเหตุที่ยังเข้าช่วยเหลือไม่สำเร็จ", Toast.LENGTH_SHORT).show();

        } else {
            tv_on_status.setText("ONLINE");
            sendDataOnline();

        }
    }

}
