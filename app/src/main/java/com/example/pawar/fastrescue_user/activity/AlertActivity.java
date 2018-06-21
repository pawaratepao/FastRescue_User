package com.example.pawar.fastrescue_user.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.pawar.fastrescue_user.R;

import java.io.IOException;

public class AlertActivity extends AppCompatActivity {
    MediaPlayer mp;
    Button btalertcommit;
    PowerManager.WakeLock screenOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_alert);

        initInstances();
        screenOn = ((PowerManager) this
                .getSystemService(this.POWER_SERVICE))
                .newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ON_AFTER_RELEASE |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP, "example");
        screenOn.acquire();

        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_ALARM);
        try {
            mp.setDataSource(this, Uri.parse("android.resource://com.example.pawar.fastrescue/" + R.raw.alarm));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initInstances() {
        btalertcommit = (Button) findViewById(R.id.btalertcommit);
        btalertcommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertActivity.this, ReciveEmerActivity.class);
                startActivity(intent);
                mp.stop();
                finish();
            }
        });


    }


}

