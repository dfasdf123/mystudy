package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ex20FriendListActivity extends AppCompatActivity {


    private String TAG = "리스트뷰예제";

    static ListView listview;

    static Ex20FriendListActivity.FriendListViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex20_friend_list);

        listview = (ListView) findViewById(R.id.listview);

        adapter = new FriendListViewAdapter();

        findViewById(R.id.btnAdd).setOnClickListener(mClickListener);
        findViewById(R.id.btnEdit).setOnClickListener(mClickListener);
        findViewById(R.id.btnDel).setOnClickListener(mClickListener);

        //리스트뷰에 Adapter 설정
        listview.setAdapter(adapter);
    }



    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v)
        {

            switch (v.getId()) {
                case R.id.btnAdd:
                    Intent intent1 = new Intent(Ex20FriendListActivity.this,Ex20AddFriendActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btnEdit:
                    Intent intent2 = new Intent(Ex20FriendListActivity.this,Ex20EditFriendActivity.class);
                    startActivity(intent2);

                    break;
                case R.id.btnDel:
                    Intent intent3 = new Intent(Ex20FriendListActivity.this,Ex20DelFriendActivity.class);
                    startActivity(intent3);
                    break;

            }
        }
    };

    public static class FriendListViewAdapter extends BaseAdapter {
        ArrayList<Ex20FriendItem> items = new ArrayList<Ex20FriendItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Ex20FriendItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public  View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final Ex20FriendItem FriendItem = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_ex20_friendlistview_list_item, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            TextView tv_name = (TextView) convertView.findViewById(R.id.tvName);
            TextView tv_hp = (TextView) convertView.findViewById(R.id.tvHp);
            TextView tv_addr = (TextView) convertView.findViewById(R.id.tvAddr);
            TextView tv_sex = (TextView) convertView.findViewById(R.id.tvSex);
            TextView tv_age = (TextView) convertView.findViewById(R.id.tvAge);

            tv_name.setText(FriendItem.getName());
            tv_hp.setText(FriendItem.getHp());
            tv_addr.setText(FriendItem.getAddr());
            tv_sex.setText(FriendItem.getSex());
            tv_age.setText(FriendItem.getAge());

            return convertView;  //뷰 객체 반환
        }
    }
}