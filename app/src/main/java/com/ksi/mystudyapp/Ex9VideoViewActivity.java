package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Ex9VideoViewActivity extends AppCompatActivity {

    Button btnPlay, btnpause, btnStop;

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex9_video_view);

        videoView = (VideoView) findViewById(R.id.videoView);

        findViewById(R.id.btnPlay).setOnClickListener(mClickListener);
        findViewById(R.id.btnpause).setOnClickListener(mClickListener);
        findViewById(R.id.btnStop).setOnClickListener(mClickListener);
        Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.jjanggu);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(videoUri);
    }
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnPlay:
                    videoView.start();
                    break;
                case R.id.btnpause:
                    videoView.pause();
                    break;
                case R.id.btnStop:
                    videoView.stopPlayback();
                    break;

            }
        }
    };
}