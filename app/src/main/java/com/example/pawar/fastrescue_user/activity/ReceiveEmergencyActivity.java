package com.example.pawar.fastrescue_user.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.fragment.ReceiveEmergencyFragment;
import com.example.pawar.fastrescue_user.fragment.ShowDirectionFragment;

public class ReceiveEmergencyActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_emergency);
        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mapContainer, ShowDirectionFragment.newInstance())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ReceiveEmergencyFragment.newInstance())
                    .commit();


        }


    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("ข้อมูลเหตุที่รับมา");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


}

