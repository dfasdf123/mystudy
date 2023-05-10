package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Ex16FrameLayoutActivity extends AppCompatActivity {

    LinearLayout frame1, frame2, frame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex16_frame_layout);

        frame1 = (LinearLayout) findViewById(R.id.frame1);
        frame2 = (LinearLayout) findViewById(R.id.frame2);
        frame3 = (LinearLayout) findViewById(R.id.frame3);

        findViewById(R.id.btnAct1).setOnClickListener(mClickListener);
        findViewById(R.id.btnAct2).setOnClickListener(mClickListener);
        findViewById(R.id.btnAct3).setOnClickListener(mClickListener);

    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v)
        {
            frame1.setVisibility(View.INVISIBLE);
            frame2.setVisibility(View.INVISIBLE);
            frame3.setVisibility(View.INVISIBLE);

            switch (v.getId()) {
                case R.id.btnAct1:
                    frame1.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnAct2:
                    frame2.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnAct3:
                    frame3.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
}