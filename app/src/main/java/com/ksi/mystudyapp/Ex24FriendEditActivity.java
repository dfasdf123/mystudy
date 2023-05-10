package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex24FriendsListActivity.adapter;
import static com.ksi.mystudyapp.Ex24FriendsListActivity.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex24FriendEditActivity extends AppCompatActivity {
    Switch swSex;
    TextView tvSex;

    EditText etName, etHp, etAddr, etAge;

    static SQLiteDatabase db;


    Button btnEdit, btnFind;
    //전달할 변수들
    String sendId, sendPw, sendName, sendHp, selectSex="남자";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex24_edit_friend);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex24FriendEditActivity.this, // 현재 화면의 context
                "friends.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        etAddr = (EditText) findViewById(R.id.etAddr);
        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etHp = (EditText) findViewById(R.id.etHp);

        swSex = (Switch) findViewById(R.id.swSex);
        tvSex = (TextView) findViewById(R.id.tvSex);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnFind = (Button) findViewById(R.id.btnFind);
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
        btnFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String findName = etName.getText().toString();
                if(findName.equals(""))
                {
                    Toast.makeText(Ex24FriendEditActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
                Cursor c = db.rawQuery("SELECT * FROM friends", null);

                while (c.moveToNext()) {

                    int idx = c.getInt(0);
                    String name = c.getString(1);
                    String hp = c.getString(2);
                    String sex = c.getString(3);
                    String addr = c.getString(4);
                    String age = c.getString(5);

                    if(findName.equals(name))
                    {
                        etHp.setText(hp);
                        if(sex.equals("남자"))
                        {
                            swSex.setChecked(false);
                        }
                        else
                        {
                            swSex.setChecked(true);
                        }
                        etAddr.setText(addr);
                        etAge.setText(age);
                    }



                }
                c.close();
                //db.close();


            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String name = etName.getText().toString();
                String hp = etHp.getText().toString();
                String addr = etAddr.getText().toString();
                String sex = selectSex;
                String findName = etName.getText().toString();

                if(name.equals("")){
                    Toast.makeText(Ex24FriendEditActivity.this,"이름을 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(hp.equals("")){
                    Toast.makeText(Ex24FriendEditActivity.this,"연락처를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(addr.equals("")){
                    Toast.makeText(Ex24FriendEditActivity.this,"주소를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etAge.getText().toString().equals("")){
                    Toast.makeText(Ex24FriendEditActivity.this,"나이를 입력하시오.",Toast.LENGTH_SHORT).show();
                    return;
                }
                //공백체크가 다 되었으면 나이는 숫자로 변환
                int age = Integer.parseInt(etAge.getText().toString());

                //리스트뷰 어댑터에 처리하는부분...
                for(int i = 0; i< adapter.getCount(); i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex25FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(adapter.items.get(i).getName()))
                    {

                        //값자리 초기화
                        adapter.items.get(i).setName(name);
                        adapter.items.get(i).setHp(hp);
                        adapter.items.get(i).setAddr(addr);
                        adapter.items.get(i).setAge(age);
                        if("남자".equals(adapter.items.get(i).getSex()))
                        {
                            adapter.items.get(i).setSex(selectSex);
                        }
                        else {
                            adapter.items.get(i).setSex(selectSex);
                        }

                        update(name,hp,sex,addr,age);
                    }
                }


                //값자리 초기화
                etName.setText("");
                etHp.setText("");
                etAddr.setText("");
                etAge.setText("");
                Toast.makeText(Ex24FriendEditActivity.this,"수정완료!",Toast.LENGTH_SHORT).show();
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void update(String name, String hp, String sex, String addr, int age) {
        db = helper.getWritableDatabase(); //db 객체를 얻어온다. 쓰기가능

        ContentValues values = new ContentValues();
        //values.put("name", name);
        values.put("hp", hp);
        values.put("sex",sex);
        values.put("addr", addr);
        values.put("age",age);
        db.update("friends", values, "name ='"+name+"'", null);

        db.close();
        Toast.makeText(getApplicationContext(), name+"의 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
        //수정완료후 초기화
        etName.setText("");
        etHp.setText("");
        etAddr.setText("");
        etAge.setText("");
    }
}