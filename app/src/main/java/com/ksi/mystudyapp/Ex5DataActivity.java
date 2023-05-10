package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Ex5DataActivity extends AppCompatActivity {
    public static String id,hp;
    EditText etstaticId, etStaticHp,etSendId,etSendHp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5_data);
        etstaticId = (EditText) findViewById(R.id.etStaticId);
        etStaticHp = (EditText) findViewById(R.id.etStaticHp);
        etSendId = (EditText) findViewById(R.id.etSendId);
        etSendHp = (EditText) findViewById(R.id.etSendHp);
        findViewById(R.id.btnSave).setOnClickListener(mClickListener);
        findViewById(R.id.btnSend).setOnClickListener(mClickListener);
    }
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSave:
                    id = etstaticId.getText().toString();
                    hp = etStaticHp.getText().toString();

                    etstaticId.setText("");
                    etStaticHp.setText("");
                    Toast.makeText(Ex5DataActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnSend:
                    String  getId = etSendId.getText().toString();
                    String  getHp = etSendHp.getText().toString();
                        Intent intent1 = new Intent(Ex5DataActivity.this,Ex5DataReceiveActivity.class);
                        intent1.putExtra("id",getId);
                        intent1.putExtra("hp",getHp);
                        startActivity(intent1);
                    break;

            }
        }
    };
}