package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;
import static com.ksi.mystudyapp.Ex24FriendsListActivity.helper;
import static com.ksi.mystudyapp.Ex28FriendsRecyclerView.dataList;

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

public class Ex28EditFriendActivity extends AppCompatActivity {

    EditText etName, etHp, etAddr,  etAge;

    TextView tvSex;

    String selectSex = "남자";

    Button btnFind,btnEdit;

    Switch swSex;

    static MySQLiteOpenHelperFriends helper;

    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex28_edit_friend);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex28EditFriendActivity.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호



        etName = (EditText) findViewById(R.id.etName);
        etHp = (EditText) findViewById(R.id.etHp);
        etAddr = (EditText) findViewById(R.id.etAddr);
        tvSex = (TextView) findViewById(R.id.tvSex);
        etAge = (EditText) findViewById(R.id.etAge);
        btnFind = (Button) findViewById(R.id.btnFind);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        swSex = (Switch) findViewById(R.id.swSex);
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

        btnFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String findName = etName.getText().toString();
                if (findName.equals("")) {
                    Toast.makeText(Ex28EditFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
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

                    if (findName.equals(name)) {
                        etHp.setText(hp);
                        if (sex.equals("남자")) {
                            swSex.setChecked(false);
                        } else {
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
                String findName = etName.getText().toString();

                if(findName.equals(""))
                {
                    Toast.makeText(Ex28EditFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex28EditFriendActivity.this, "연락처를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex28EditFriendActivity.this, "주소를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex28EditFriendActivity.this, "나이를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = etName.getText().toString();
                String hp = etHp.getText().toString();
                String addr = etAddr.getText().toString();
                String sex = selectSex;
                int age = Integer.parseInt(etAge.getText().toString());

                //리스트뷰 어댑터에 처리하는부분...
                for(int i=0; i<Ex28FriendsRecyclerView.adapter.getItemCount();i++)
                {

                    if(findName.equals(dataList.get(i).getName()))
                    {

                        dataList.get(i).setName(name);
                        dataList.get(i).setHp(hp);
                        dataList.get(i).setAddr(addr);
                        dataList.get(i).setAge(age);


                        if("남자".equals(dataList.get(i).getSex()))
                        {
                            dataList.get(i).setSex(selectSex);
                        }
                        else {
                            dataList.get(i).setSex(selectSex);
                        }
                        update(name,hp,sex,addr,age);
                    }
                    Toast.makeText(Ex28EditFriendActivity.this, "수정완료!", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etHp.setText("");
                    etAddr.setText("");
                    etAge.setText("");
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                Ex28FriendsRecyclerView.adapter.notifyDataSetChanged();
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