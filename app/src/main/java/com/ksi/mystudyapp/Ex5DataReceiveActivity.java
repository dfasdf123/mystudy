package com.ksi.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ex5DataReceiveActivity extends AppCompatActivity {

    TextView tvId, tvHp,tvReturnId,tvReturnHp;
    Button btnRecall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5_receivedata);

        tvId = (TextView) findViewById(R.id.tvId);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvReturnId = (TextView) findViewById(R.id.tvReturnId);
        tvReturnHp = (TextView) findViewById(R.id.tvReturnHp);
        btnRecall = (Button) findViewById(R.id.btnRecall);

        //전달받은 데이타 받기
        Intent getData =  getIntent();
        String id = getData.getStringExtra("id");
        String hp = getData.getStringExtra("hp");
        tvReturnId.setText(id);
        tvReturnHp.setText(hp);

        btnRecall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                tvId.setText(Ex5DataActivity.id);
                tvHp.setText(Ex5DataActivity.hp);
            }
        });

    }


}