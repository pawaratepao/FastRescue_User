package com.example.pawar.fastrescue_user.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.StatusItemDao;
import com.example.pawar.fastrescue_user.fragment.ListViewStatusFragment;

public class StatusActivity extends AppCompatActivity implements ListViewStatusFragment.ListViewStatusFragmentListener {
    Toolbar toolbar;
    ProgressDialog progress;
    String off_latitude;
    String off_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListViewStatusFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("สถานะเหตุที่แจ้ง");
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
    public void onStatusItemClicked(StatusItemDao dao) {
        Intent intent = new Intent(StatusActivity.this, StatusDetailActivity.class);
        intent.putExtra("dao", dao);
        startActivity(intent);


    }

}



