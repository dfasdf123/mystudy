package com.ksi.mystudyapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex24FriendAddActivity extends AppCompatActivity {
    Switch swSex;
    TextView tvSex;

    EditText etName, etHp, etAddr, etAge;


    Button btnAdd;
    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, selectSex="남자";


    MySQLiteOpenHelperFriends helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex24_add_friend);


        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex24FriendAddActivity.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etAddr = (EditText) findViewById(R.id.etAddr);
        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etHp = (EditText) findViewById(R.id.etHp);

        swSex = (Switch) findViewById(R.id.swSex);
        tvSex = (TextView) findViewById(R.id.tvSex);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        swSex = findViewById(R.id.swSex);
        swSex.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tvSex.setText("여자");
                    selectSex ="여자";
                } else {
                    tvSex.setText("남자");
                    selectSex = "남자";
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String name = etName.getText().toString().trim();
                String hp = etHp.getText().toString().trim();
                String addr = etAddr.getText().toString().trim();
                String sex = selectSex;

                if(name.equals("")){
                    Toast.makeText(Ex24FriendAddActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(hp.equals("")){
                    Toast.makeText(Ex24FriendAddActivity.this,"연락처를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(addr.equals("")){
                    Toast.makeText(Ex24FriendAddActivity.this,"주소를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etAge.getText().toString().equals("")){
                    Toast.makeText(Ex24FriendAddActivity.this,"나이를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                //공백체크가 다 되었으면 나이는 숫자로 변환
                int age = Integer.parseInt(etAge.getText().toString());

                insert(name,hp,sex,addr,age+"");
                //리스트뷰 어댑터에 처리하는부분...
                //Ex25FriendsListActivity.addData(name,hp,addr,sex,age);

                //값자리 초기화
                etName.setText("");
                etHp.setText("");
                etAddr.setText("");
                etAge.setText("");

                Toast.makeText(Ex24FriendAddActivity.this,"친구가 추가됨!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //데이타베이스 메서드 처리  ////////////////////////////
    public void insert(String name, String hp, String sex, String addr, String age) {

        SQLiteDatabase db = helper.getWritableDatabase(); // db 객체를 얻어온다. 쓰기 가능

        //값들을 컨트롤 하려고 클래스 생성
        ContentValues values = new ContentValues();

        // db.insert의 매개변수인 values가 ContentValues 변수이므로 그에 맞춤
        // 데이터의 삽입은 put을 이용한다.
        values.put("name", name);
        values.put("hp", hp);
        values.put("sex", sex);
        values.put("addr", addr);
        values.put("age", age);
        db.insert("friends", null, values); // 테이블/널컬럼핵/데이터(널컬럼핵=디폴트)

        // tip : 마우스를 db.insert에 올려보면 매개변수가 어떤 것이 와야 하는지 알 수 있다.
        db.close();
        Toast.makeText(getApplicationContext(), name+"로 친구추가 완료.", Toast.LENGTH_LONG).show();

        Log.d("친구추가", name+"/"+hp+"/"+sex+"/"+addr+"/"+age+" 의 정보로 디비저장완료.");
        Ex24FriendsListActivity.selectFriendsList();
    }
}