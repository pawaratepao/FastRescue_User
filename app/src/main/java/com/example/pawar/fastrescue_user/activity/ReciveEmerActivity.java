package com.example.pawar.fastrescue_user.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.EmerItemDao;
import com.example.pawar.fastrescue_user.fragment.ListViewEmerFragment;

public class ReciveEmerActivity extends AppCompatActivity implements ListViewEmerFragment.ListViewEmerFragmentListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_emer);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListViewEmerFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("เหตุที่แจ้งทั้งหมด");
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
    public void onEmerItemClicked(EmerItemDao dao) {
        Intent intent = new Intent(ReciveEmerActivity.this, EmerDetailActivity.class);
        intent.putExtra("dao", dao);
        startActivity(intent);
    }

    @Override
    public boolean isFinishing() {
        SharedPreferences sp = getSharedPreferences("CHECK_NOTI", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("first_data", true);
        editor.commit();
        return super.isFinishing();
    }
}
