package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ex10MediaActivity extends AppCompatActivity {

    Button btnPlay, btnpause, btnStop;

    TextView tvNow;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex10_media);

        tvNow = (TextView) findViewById(R.id.tvNow);

        findViewById(R.id.btnPlay).setOnClickListener(mClickListener);
        findViewById(R.id.btnpause).setOnClickListener(mClickListener);
        findViewById(R.id.btnStop).setOnClickListener(mClickListener);

            player = MediaPlayer.create(Ex10MediaActivity.this, R.raw.blue);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnPlay:
                    player.start();
                    tvNow.setText("노래 재생중...");
                    break;
                case R.id.btnpause:
                    tvNow.setText("노래일시정지중...");
                    player.pause();
                    break;
                case R.id.btnStop:
                    player.stop();
                    tvNow.setText("음악플레이대기중...");
                    break;

            }
        }
    };
}