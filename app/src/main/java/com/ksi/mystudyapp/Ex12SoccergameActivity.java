package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Ex12SoccergameActivity extends AppCompatActivity {

    ImageView ivMain;

    TextView tvChance, tvResult, tvTot, tvGoal, tvFail;

    int chance=10, tot, goal ,fail;

    boolean shootChance = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex12_soccergame);

        tvChance = (TextView) findViewById(R.id.tvChance);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvTot = (TextView) findViewById(R.id.tvTot);
        tvGoal = (TextView) findViewById(R.id.tvGoal);
        tvFail = (TextView) findViewById(R.id.tvFail);
        ivMain = (ImageView) findViewById(R.id.ivMain);


        findViewById(R.id.btnLong).setOnClickListener(mClickListener);
        findViewById(R.id.btnPk).setOnClickListener(mClickListener);
        findViewById(R.id.btnHead).setOnClickListener(mClickListener);
        findViewById(R.id.btnRestart).setOnClickListener(mClickListener);
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnLong:
                    if(chance > 1)
                    {
                        shootChance = true;
                        chance -= 2;
                        tvChance.setText("슛기회: " + chance + "회");
                        int rand = (int) (Math.random() * 100);
                        String longReuslt =  randLong(rand);
                        if(longReuslt.equals("노골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.jungno).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    tvResult.setText("결과: 노 골!");
                                    tot++;
                                    fail++;
                                    tvTot.setText("총"+tot+"회");
                                    tvFail.setText("실패:"+fail+"회");

                                }
                            },3200);


                        }
                        else if(longReuslt.equals("골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.jungyes).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    tvResult.setText("결과:  골!!");
                                    tot++;
                                    goal++;
                                    tvTot.setText("총"+tot+"회");
                                    tvGoal.setText("골:"+goal+"회");

                                }
                            },2200);


                        }

                    }
                    else if(chance == 0)
                     {
                         shootChance = false;
                         Toast.makeText(Ex12SoccergameActivity.this,
                                 "게임다시하기를 눌러 재시작하세요!",
                                 Toast.LENGTH_SHORT).show();
                     }

                    break;
                case R.id.btnPk:
                    if(chance > 2)
                    {
                        shootChance = true;
                        chance -= 3;
                        tvChance.setText("슛기회: " + chance + "회");
                        int rand = (int) (Math.random() * 100);
                        String pkReuslt =  randPk(rand);
                        if(pkReuslt.equals("노골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.pkno).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    tvResult.setText("결과: 노 골!");
                                    tot++;
                                    fail++;
                                    tvTot.setText("총"+tot+"회");
                                    tvFail.setText("실패:"+fail+"회");

                                }
                            },14200);

                        }
                        else if(pkReuslt.equals("골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.pkyes).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    tvResult.setText("결과:  골!!");
                                    tot++;
                                    goal++;
                                    tvTot.setText("총"+tot+"회");
                                    tvGoal.setText("골:"+goal+"회");
                                }
                            },1800);
                        }

                    }


                    else if(chance == 0)
                    {
                        shootChance = false;
                        Toast.makeText(Ex12SoccergameActivity.this,
                                "게임다시하기를 눌러 재시작하세요!",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;


                case R.id.btnHead:
                    if(chance > 0)
                    {
                        shootChance = true;
                        chance -= 1;
                        tvChance.setText("슛기회: " + chance + "회");

                        int rand = (int) (Math.random() * 100);
                        String headReuslt =  randHead(rand);
                        if(headReuslt.equals("노골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.headerno).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {

                                    tvResult.setText("결과: 노 골!");
                                    tot++;
                                    fail++;
                                    tvTot.setText("총"+tot+"회");
                                    tvFail.setText("실패:"+fail+"회");

                                }
                            },2200);



                        }
                        else if(headReuslt.equals("골"))
                        {
                            tvResult.setText("결과: 대기중...");
                            Glide.with(Ex12SoccergameActivity.this).load(R.drawable.headeryes).into(ivMain);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    tvResult.setText("결과:  골!!");
                                    tot++;
                                    goal++;
                                    tvTot.setText("총"+tot+"회");
                                    tvGoal.setText("골:"+goal+"회");

                                }
                            },7000);
                        }

                    }
                    else if(chance == 0)
                    {
                        shootChance = false;
                        Toast.makeText(Ex12SoccergameActivity.this,
                                "게임다시하기를 눌러 재시작하세요!",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.btnRestart:
                    tvResult.setText("결과: 대기중...");
                    chance=10;
                    goal=0;
                    fail=0;
                    tot=0;
                    tvChance.setText("슛기회: " + chance + "회");
                    tvTot.setText("총"+tot+"회");
                    tvGoal.setText("골:"+goal+"회");
                    tvFail.setText("실패:"+fail+"회");
                    ivMain.setImageResource(R.drawable.soccer);
                    break;

            }
        }
    };
    String randLong(int rand)
    {
        if(rand < 49) {return "노골";}
        else if(rand > 50){return "골";}
        else {return "";}
    }

    String randPk(int rand)
    {
        if(rand < 9) {return "노골";}
        else if(rand > 10){return "골";}
        else {return "";}
    }

    String randHead(int rand)
    {
        if(rand > 30) {return "노골";}
        else if(rand < 29){return "골";}
        else {return "";}
    }
}