package com.ksi.mystudyapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Ex28FriendsRecyclerView extends AppCompatActivity implements Ex28FriendsRecyclerAdapter.MyRecyclerViewClickListener {

    static ArrayList<Ex28FriendsItem> dataList = new ArrayList<>();


    final static Ex28FriendsRecyclerAdapter adapter = new Ex28FriendsRecyclerAdapter(dataList);

    static MySQLiteOpenHelperFriends helper;

    static SQLiteDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex28_friends_recyclerview);

        //데이베이스 생성.
        helper = new MySQLiteOpenHelperFriends(
                Ex28FriendsRecyclerView.this, // 현재 화면의 context
                "friends2.db", // 파일명
                null, // 커서 팩토리
                1); // 버전 번호

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        findViewById(R.id.btnAdd).setOnClickListener(mClick);
        findViewById(R.id.btnEdit).setOnClickListener(mClick);
        findViewById(R.id.btnDel).setOnClickListener(mClick);

        selectFriendsList();
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);

    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnAdd:
                    Intent intentAdd = new Intent(Ex28FriendsRecyclerView.this, Ex28AddFriendActivity.class);
                    startActivity(intentAdd);
                    break;
                case R.id.btnEdit:
                    Intent intentEdit = new Intent(Ex28FriendsRecyclerView.this, Ex28EditFriendActivity.class);
                    startActivity(intentEdit);
                    break;
                case R.id.btnDel:
                    Intent intentDel = new Intent(Ex28FriendsRecyclerView.this, Ex28DelFriendActivity.class);
                    startActivity(intentDel);
                    break;


            }

        }
    };
    public static void selectFriendsList() {
        //초기화후 다시~!
        dataList.clear();

        db = helper.getReadableDatabase(); // db객체를 얻어온다. 읽기 전용
        Cursor c = db.rawQuery("SELECT * FROM friends", null);

        while (c.moveToNext()) {

            int idx = c.getInt(0);
            String name = c.getString(1);
            String hp = c.getString(2);
            String sex = c.getString(3);
            String addr = c.getString(4);
            String age = c.getString(5);

            dataList.add(new Ex28FriendsItem(name,hp,sex,addr,Integer.parseInt(age)));

        }
        c.close();
        //db.close();


    }

    @Override
    public void onNameClicked(int position) {
        Toast.makeText(getApplicationContext(), "Name : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onHpClicked(int position) {
        Toast.makeText(getApplicationContext(), "Hp : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onSexClicked(int position) {
        Toast.makeText(getApplicationContext(), "Sex : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onAddrClicked(int position) {
        Toast.makeText(getApplicationContext(), "Addr : "+position, Toast.LENGTH_SHORT).show();
    }

    public void onAgeClicked(int position) {
        Toast.makeText(getApplicationContext(), "Age : "+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClicked(int position) {

    }

}