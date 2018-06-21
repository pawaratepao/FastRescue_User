package com.example.pawar.fastrescue_user.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.fragment.AlertDetailFragment;
import com.example.pawar.fastrescue_user.fragment.ShowMapFragment;

public class AlertDetailActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);
        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mapContainer, ShowMapFragment.newInstance())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, AlertDetailFragment.newInstance())
                    .commit();


        }


    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void clearTempReceive() {
        SharedPreferences sp = getSharedPreferences("EMER_DETAIL", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putBoolean("filestatus", false);
        editor.commit();
    }


}


