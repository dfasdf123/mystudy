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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ex19NumberListView extends AppCompatActivity {

    private String TAG = "리스트뷰예제";
    private ListView listview = null;
    private Ex19NumberListView.ListViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex19_number_list_view);

        listview = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter();

        //Adapter 안에 아이템의 정보 담기
        adapter.addItem(new Ex19NumberItem("홍길동1", "010-1234-1234", R.drawable.tel));
        adapter.addItem(new Ex19NumberItem("홍길동2", "010-1111-2222", R.drawable.tel));
        adapter.addItem(new Ex19NumberItem("홍길동3", "010-3333-4444", R.drawable.tel));
        adapter.addItem(new Ex19NumberItem("홍길동4", "010-5555-6666", R.drawable.tel));
        adapter.addItem(new Ex19NumberItem("홍길동5", "010-7777-8888", R.drawable.tel));

        //리스트뷰에 Adapter 설정
        listview.setAdapter(adapter);
    }
    public class ListViewAdapter extends BaseAdapter {
        ArrayList<Ex19NumberItem> items = new ArrayList<Ex19NumberItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(Ex19NumberItem item) {
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
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final Ex19NumberItem numItem = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_ex19_numberlistview_list_item, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_hp = (TextView) convertView.findViewById(R.id.tv_hp);
            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);

            tv_name.setText(numItem.getName());
            tv_hp.setText(numItem.getHp());
            iv_icon.setImageResource(numItem.getResId());
            Log.d(TAG, "getView() - [ "+position+" ] "+numItem.getHp());

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String tel_name1 = "tel:"+tv_name.getText().toString();
                    String tel_number1 = "tel:"+tv_hp.getText().toString();

                    Intent intentTel1 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number1));
                    startActivity(intentTel1);
                    Toast.makeText(Ex19NumberListView.this,tel_name1+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }
}