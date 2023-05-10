package com.ksi.mystudyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Ex30webPhpLogin extends AppCompatActivity {

    EditText etId, etPw;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex30_web_login_layout);

        etId = (EditText)findViewById(R.id.etId);
        etPw = (EditText)findViewById(R.id.etPw);

        findViewById(R.id.btnLogin).setOnClickListener(mClickListener);
        findViewById(R.id.btnJoin).setOnClickListener(mClickListener);
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v){


            switch (v.getId()) {
                case R.id.btnLogin :
                    String id = etId.getText().toString();
                    String pw = etPw.getText().toString();
                    Log.d("로그인성공","11 id:"+ id);

                    if(id.equals(""))
                    {
                        Toast.makeText(Ex30webPhpLogin.this,"아이디를 입력하시오.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pw.equals(""))
                    {
                        Toast.makeText(Ex30webPhpLogin.this,"비번을 입력하시오..",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    login(id,pw);
                    break;
                case R.id.btnJoin :
                    Intent intentJoin = new Intent(Ex30webPhpLogin.this, Ex30webPhpJoin.class);
                    startActivity(intentJoin);
                    break;

            }
        }
    };

    void login(String id,String pw)
    {
        Log.d("로그인성공","22 id:"+ id);
        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if(success) {
                        etId.setText("");
                        etPw.setText("");
                        Intent intentMain = new Intent(Ex30webPhpLogin.this, Ex30webPhpMain.class);
                        startActivity(intentMain);
                    }
                    else {
                        Toast.makeText(Ex30webPhpLogin.this,"로그인실패!아이디/비번확인해주세요!",Toast.LENGTH_SHORT).show();
                        Log.d("로그인실패","로그인 실패 아이디 비번 확인하시오!");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ///111111111111111111111111111111111111111111111
        Ex30ServerRequestLogin getUserCharLevelStat = new Ex30ServerRequestLogin(id, pw, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Ex30webPhpLogin.this);
        queue.add(getUserCharLevelStat);

    }

}