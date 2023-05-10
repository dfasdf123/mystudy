package com.ksi.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    Animation ani1,ani2;

    TextView tvMent;

    ImageView ivJanggu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ivJanggu = (ImageView) findViewById(R.id.ivJanggu);
        tvMent = (TextView) findViewById(R.id.tvMent);
            ani1 = AnimationUtils.loadAnimation(IntroActivity.this,R.anim.alpha);
        ani2 = AnimationUtils.loadAnimation(IntroActivity.this,R.anim.blink);

        ivJanggu.startAnimation(ani1);
        tvMent.startAnimation(ani2);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run()
            {
                 Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                 startActivity(intent);
            }
        },3700);
    }
}