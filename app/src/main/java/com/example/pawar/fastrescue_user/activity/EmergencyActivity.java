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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.ServerResponse;
import com.example.pawar.fastrescue_user.fragment.EmergencyFragment;
import com.example.pawar.fastrescue_user.fragment.MapFragment;
import com.example.pawar.fastrescue_user.fragment.TabEmerFragment;
import com.example.pawar.fastrescue_user.manager.ActivityResultBus;
import com.example.pawar.fastrescue_user.manager.ActivityResultEvent;
import com.example.pawar.fastrescue_user.manager.Contextor;
import com.example.pawar.fastrescue_user.manager.HttpMeneger;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, EmergencyFragment.EmergencyFragmentListener {
    private static final String URL = "http://pohtecktung.welovepc.com/newproject/android/upload_detail.php";
    Toolbar toolbar;
    String outputPath, noti_id, sLat, sLng, id, sn;
    Boolean SendStatus;
    ProgressDialog progress;
    Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    Double latitude, longitude;
    String[] photo;
    int n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, TabEmerFragment.newInstance(), "EmergencyFragment")
                    .commit();


        }
    }

    private void initInstances() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("แจ้งเหตุ");


        SendStatus = false;

        getUsername();


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (SendStatus == false) {
            RemoveFailSend();
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // You have to save path in case your activity is killed.
        // In such a scenario, you will need to re-initialize the CameraImagePicker
        outState.putString("picker_path", outputPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // After Activity recreate, you need to re-initialize these
        // two values to be able to re-initialize CameraImagePicker
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("picker_path")) {
                outputPath = savedInstanceState.getString("picker_path");
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultBus.getInstance().postQueue(
                new ActivityResultEvent(requestCode, resultCode, data));

    }


    public void getUsername() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("PREF_Login", MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);

    }

    @Override
    public void onGetPhotoClicked(String noti) {
        if (noti_id == null) {
            noti_id = noti;
        } else
            noti_id = noti_id + "," + noti;
        Toast.makeText(this, noti_id, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSendEmergency(final String sEmergency, final String sEmergencyDetail, final String sEmergencyStatus) {
        photo = noti_id.split(",");
        n = photo.length;
        sn = String.valueOf(n);

        Toast.makeText(EmergencyActivity.this, "" + sEmergency + "" + sEmergencyDetail + "" + sEmergencyStatus, Toast.LENGTH_SHORT).show();
        if (sLat == null || sLng == null) {
            Toast.makeText(EmergencyActivity.this, "กรุณากดรปุ่มรับตำแหน่งหรือตรวจสอบ GPS", Toast.LENGTH_SHORT).show();
        } else if (noti_id == null) {
            Toast.makeText(EmergencyActivity.this, "กรุณากดรปุ่มเพิ่มรูปภาพก่อนครับ", Toast.LENGTH_SHORT).show();
        } else if (sEmergency == null || sEmergencyDetail == null || sEmergencyStatus == null) {
            Toast.makeText(EmergencyActivity.this, "กรุณาเลือกเหตุและใส่รายละเอียด", Toast.LENGTH_SHORT).show();

        } else {

            progress = ProgressDialog.show(this, "โปรดรอสักครู่",
                    "Loading...", true);

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    Toast.makeText(EmergencyActivity.this, response, Toast.LENGTH_SHORT).show();
                    Toast.makeText(EmergencyActivity.this, "เพิ่มข้อมูลแล้วจ้า", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmergencyActivity.this, MainActivity.class);
                    startActivity(intent);
                    noti_id = null;
                    SendStatus = true;
                    progress.dismiss();
                    finish();

                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError", error.toString());
                    Toast.makeText(EmergencyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(EmergencyActivity.this, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("noti_event", sEmergency);
                    params.put("noti_detail", sEmergencyDetail);
                    params.put("noti_latitude", sLat);
                    params.put("noti_longitude", sLng);
                    params.put("noti_pstatus", sEmergencyStatus);
                    params.put("noti_user", id);
                    params.put("n", sn);
                    for (int i = 0; i <= n-1; i++) {
                        params.put("pic" + i, photo[i]);
                    }

                    return params;
                }
            };
            requestQueue.add(request);
        }

    }

    public void RemoveFailSend() {

        Call<ServerResponse> call = HttpMeneger.getInstance().getService().RemoveFailSend(noti_id);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    ServerResponse serverResponse = response.body();
                    if (serverResponse.getSuccess() == true) {
                        Toast.makeText(EmergencyActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EmergencyActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Log.v("Response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(Contextor.getInstance().getContext(),
                        "123" + t.toString(),
                        Toast.LENGTH_LONG).show();
                // handle error.
            }
        });
    }

    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
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
                sLat = latitude.toString();
                sLng = longitude.toString();
                Toast.makeText(this, sLat + "," + sLng, Toast.LENGTH_SHORT).show();
            } else

            {
                // Do something when location provider not available
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
        final String message = "คุณยังไม่ได้เปิดใช้งาน GPS จะทำการเปิดใช้งานหรือไม่";

        builder.setMessage(message)
                .setPositiveButton("ใช่",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("ยกเลิก",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.finish();
                                d.cancel();

                            }
                        });
        builder.create().show();
    }


}



