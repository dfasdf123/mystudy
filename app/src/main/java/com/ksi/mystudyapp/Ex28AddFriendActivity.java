package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;
import static com.ksi.mystudyapp.Ex28FriendsRecyclerView.dataList;

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

public class Ex28AddFriendActivity extends AppCompatActivity {

    EditText etName, etHp, etAddr,  etAge;

    TextView tvSex;

    String selectSex = "남자";

    Switch swSex;

    MySQLiteOpenHelperFriends helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex28_add_friend);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex28AddFriendActivity.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호



        etName = (EditText) findViewById(R.id.etName);
        etHp = (EditText) findViewById(R.id.etHp);
        etAddr = (EditText) findViewById(R.id.etAddr);
        tvSex = (TextView) findViewById(R.id.tvSex);
        etAge = (EditText) findViewById(R.id.etAge);
        swSex = (Switch) findViewById(R.id.swSex);


        findViewById(R.id.btnAdd).setOnClickListener(mClickListener);

        swSex.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tvSex.setText("여자");
                    selectSex = "여자";
                } else {
                    tvSex.setText("남자");
                    selectSex = "남자";
                }
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
        Ex28FriendsRecyclerView.selectFriendsList();
    }

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v)
        {
            String name = etName.getText().toString();
            String hp = etHp.getText().toString();
            String addr = etAddr.getText().toString();
            String sex = selectSex;
            String age = etAge.getText().toString();


            switch (v.getId()) {
                case R.id.btnAdd:
                    if(name.equals(""))
                    {
                        Toast.makeText(Ex28AddFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(hp.equals(""))
                    {
                        Toast.makeText(Ex28AddFriendActivity.this, "연락처를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(addr.equals(""))
                    {
                        Toast.makeText(Ex28AddFriendActivity.this, "주소를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(age.equals(""))
                    {
                        Toast.makeText(Ex28AddFriendActivity.this, "나이를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    insert(name,hp,sex,addr,age);
                    dataList.add(new Ex28FriendsItem(name,hp,sex,addr,Integer.parseInt(age)));
                    Ex28FriendsRecyclerView.adapter.notifyDataSetChanged();
                    etName.setText("");
                    etHp.setText("");
                    etAddr.setText("");
                    etAge.setText("");
                    Toast.makeText(Ex28AddFriendActivity.this, "친구추가 완료!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}