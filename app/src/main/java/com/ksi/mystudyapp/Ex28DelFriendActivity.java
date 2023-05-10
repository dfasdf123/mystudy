package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;
import static com.ksi.mystudyapp.Ex24FriendsListActivity.helper;
import static com.ksi.mystudyapp.Ex28FriendsRecyclerView.dataList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex28DelFriendActivity extends AppCompatActivity {
    TextView tvHp,tvSex, tvAddr, tvAge;
    EditText etName;

    Button btnDel, btnFind;
    //전달할 변수들
    String selectSex="남자";

    static MySQLiteOpenHelperFriends helper;

    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex28_del_friend);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex28DelFriendActivity.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        etName = (EditText) findViewById(R.id.etName);

        tvAddr = (TextView) findViewById(R.id.tvAddr);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvHp = (TextView) findViewById(R.id.tvHp);
        tvSex = (TextView) findViewById(R.id.tvSex);

        btnFind = (Button) findViewById(R.id.btnFind);
        btnDel = (Button) findViewById(R.id.btnDel);

        btnFind.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String findName = etName.getText().toString();
                if(findName.equals(""))
                {
                    Toast.makeText(Ex28DelFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
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
                        tvHp.setText(hp);
                        tvSex.setText(sex);
                        tvAddr.setText(addr);
                        tvAge.setText(age);
                    }
                }
                c.close();
                //db.close();



            }
        });
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String findName = etName.getText().toString();

                for(int i=0; i<Ex28FriendsRecyclerView.adapter.getItemCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex20FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(dataList.get(i).getName()))
                    {

                        delete(findName);
                        Ex28FriendsRecyclerView.adapter.remove(i);
                        Ex28FriendsRecyclerView.adapter.notifyDataSetChanged();

                        break;
                    }
                }
                Toast.makeText(Ex28DelFriendActivity.this, "삭제완료!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Ex28DelFriendActivity.this,Ex28FriendsRecyclerView.class);
                startActivity(intent);
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                Ex28FriendsRecyclerView.adapter.notifyDataSetChanged();
            }
        });
    }
    public void delete(String delId) {
        db = helper.getWritableDatabase();
        db.delete("friends", "name='"+delId+"'", null);
        Log.d("db", delId + "가 정상적으로 삭제 되었습니다.");
        Toast.makeText(getApplicationContext(), delId+"의 정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        db.close();

        //삭제후처리
        etName.setText("");

    }
}