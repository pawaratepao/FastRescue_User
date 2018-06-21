package com.example.pawar.fastrescue_user.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.pawar.fastrescue_user.R;
import com.example.pawar.fastrescue_user.dao.PhotoItemDao;
import com.example.pawar.fastrescue_user.fragment.ListViewNewsFragment;

public class NewsActivity extends AppCompatActivity implements ListViewNewsFragment.ListViewNewsFragmentListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, ListViewNewsFragment.newInstance())
                    .commit();
        }

    }
    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("ข่าวสาร");
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
    public void onPhotoItemClicked(PhotoItemDao dao) {
        Intent intent = new Intent(NewsActivity.this,NewsDetailActivity.class);
        intent.putExtra("dao" ,dao);
        startActivity(intent);

    }
}