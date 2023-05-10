package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;
import static com.ksi.mystudyapp.Ex20FriendListActivity.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex20DelFriendActivity extends AppCompatActivity {
    TextView tvHp,tvSex, tvAddr, tvAge;
    EditText etName;

    Button btnDel, btnFind;
    //전달할 변수들
    String selectSex="남자";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex20_del_friend);


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
                    Toast.makeText(Ex20DelFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0; i<adapter.getCount();i++)
                {

                    if(findName.equals(adapter.items.get(i).getName()))
                    {
                        //값자리 초기화
                        etName.setText(adapter.items.get(i).getName());
                        tvHp.setText(adapter.items.get(i).getHp());
                        tvAddr.setText(adapter.items.get(i).getAddr());
                        tvAge.setText(adapter.items.get(i).getAge()+"");
                        tvSex.setText(adapter.items.get(i).getSex());

                        Toast.makeText(Ex20DelFriendActivity.this, "대상을 찾았습니다.", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                String findName = etName.getText().toString();

                for(int i=0; i<adapter.getCount();i++)
                {
                    //Log.d("아이템값체크","아아템:"+Ex20FriendsListActivity.adapter.items.get(i).getName());
                    if(findName.equals(adapter.items.get(i).getName()))
                    {
                        adapter.items.remove(i);
                        break;
                    }
                }
                Toast.makeText(Ex20DelFriendActivity.this, "삭제완료!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Ex20DelFriendActivity.this,Ex20FriendListActivity.class);
                startActivity(intent);
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                adapter.notifyDataSetChanged();
            }
        });
    }
}