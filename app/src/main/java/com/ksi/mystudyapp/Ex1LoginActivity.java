package com.ksi.mystudyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex1LoginActivity extends AppCompatActivity {
    EditText etId, etPw;

    SQLiteDatabase db;

    MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1login);

        helper = new MySQLiteOpenHelper(
                Ex1LoginActivity.this, // 현재 화면의 context
                "member.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etId = (EditText) findViewById(R.id.etId);
        etPw = (EditText) findViewById(R.id.etPw);
        findViewById(R.id.btnlogin).setOnClickListener(mClickListener);
        findViewById(R.id.btnjoin).setOnClickListener(mClickListener);
    }

    //버튼이벤트를 감지함...
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            String id = etId.getText().toString();
            String pw = etPw.getText().toString();

            switch (v.getId()) {
                case R.id.btnlogin:

                    if(id.equals(""))
                    {
                        Toast.makeText(Ex1LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(pw.equals(""))
                    {
                        Toast.makeText(Ex1LoginActivity.this, "패스워드를 입력하세요.", Toast.LENGTH_SHORT).show();
                    }

                    boolean loginCheck = false;

                    //DB 테이블에 id,pw 보내서 로그인 처리//

                    loginCheck  = dbLoginCheck(id,pw);

                    if(loginCheck ==true)
                    {
                        Intent intent = new Intent(Ex1LoginActivity.this,Ex1Member.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Ex1LoginActivity.this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }


                    break;
                case R.id.btnjoin:
                    Intent join = new Intent(Ex1LoginActivity.this, Ex1JoinActivity.class);
                    startActivity(join);
                    break;
              }
            }
        };

        public boolean dbLoginCheck(String loginId, String loginPw )
        {

            db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
            Cursor c = db.rawQuery("SELECT * FROM member", null);

            while (c.moveToNext()) {

                int idx = c.getInt(0);
                String id = c.getString(1);
                String pw = c.getString(2);
                String name = c.getString(3);
                String hp = c.getString(4);
                String addr = c.getString(5);

                Log.d("로그인", "idx: " + idx + ", id : " + id + ", name : " + name
                        + ", hp : " + hp+ ", addr : " + addr);
                if(loginId.equals(id))
                {
                    if(loginPw.equals(pw))
                    {
                        //아이디 패스워드를 최종적으로 잘 맞을경우 다 닫고 true값 리턴
                        c.close();
                        db.close();
                        Log.d("로그인-성공", "idx: " + idx + ", id : " + id + ", name : " + name
                                + ", hp : " + hp+ ", addr : " + addr);
                        return true;
                    }
                }


            }
            c.close();
            db.close();


            return false;
        }



}