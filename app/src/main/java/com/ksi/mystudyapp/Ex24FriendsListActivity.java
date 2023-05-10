package com.ksi.mystudyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Ex24FriendsListActivity extends AppCompatActivity {
    static ListView listView=null;
    static Ex24ListViewFriednsAdapter adapter=null;

    static SQLiteDatabase db;
    static MySQLiteOpenHelperFriends helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex24_friend_list);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex24FriendsListActivity.this, // 현재 화면의 context
                "friends.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호


        //id연동
        listView = (ListView)findViewById(R.id.listview);

        //버튼 이벤트처리
        findViewById(R.id.btnAdd).setOnClickListener(mClick);
        findViewById(R.id.btnEdit).setOnClickListener(mClick);
        findViewById(R.id.btnDel).setOnClickListener(mClick);

        adapter  = new Ex24ListViewFriednsAdapter();
        selectFriendsList();



    }

    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnAdd:
                    Intent intentAdd = new Intent(Ex24FriendsListActivity.this, Ex24FriendAddActivity.class);
                    startActivity(intentAdd);
                    break;
                case R.id.btnEdit:
                    Intent intentEdit = new Intent(Ex24FriendsListActivity.this, Ex24FriendEditActivity.class);
                    startActivity(intentEdit);
                    break;
                case R.id.btnDel:
                    Intent intentDel = new Intent(Ex24FriendsListActivity.this, Ex24FriendDelActivity.class);
                    startActivity(intentDel);
                    break;


            }

        }
    };

    static void addData(String name, String hp , String addr, String sex, int age)
    {
        adapter.addItem(new Ex24FriendsItem(name,hp,sex,addr,age));
        listView.setAdapter(adapter);

    }

    public static void selectFriendsList() {
       //초기화후 다시~!
        adapter.items.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM friends", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String name = c.getString(1);
            String hp = c.getString(2);
            String sex = c.getString(3);
            String addr = c.getString(4);
            String age = c.getString(5);

           adapter.addItem(new Ex24FriendsItem(name,hp,sex,addr,Integer.parseInt(age)));

        }
        c.close();
        //db.close();
        listView.setAdapter(adapter);


    }
}