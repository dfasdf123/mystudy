package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ex1JoinInfoActivity extends AppCompatActivity {

    TextView tvId, tvPw, tvName, tvHp,  tvAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1_join_info);

        tvId = (TextView) findViewById(R.id.tvId);
        tvPw = (TextView) findViewById(R.id.tvPw);
        tvName = (TextView) findViewById(R.id.tvName);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvAddr = (TextView) findViewById(R.id.tvAddr);


        //전달받은 데이타 받기
        Intent getData =  getIntent();
        String id = getData.getStringExtra("id");
        String pw = getData.getStringExtra("pw");
        String name = getData.getStringExtra("name");
        String hp = getData.getStringExtra("hp");
        String addr = getData.getStringExtra("addr");

        tvId.setText(id);
        tvPw.setText(pw);
        tvName.setText(name);
        tvHp.setText(hp);
        tvAddr.setText(addr);

        findViewById(R.id.btnOk).setOnClickListener(mClickListener);

    }
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v)
        {
            switch (v.getId()) {
                case R.id.btnOk:
                   Intent intent = new Intent(Ex1JoinInfoActivity.this,Ex1LoginActivity.class);
                   startActivity(intent);
                    break;



            }
        }
    };
}