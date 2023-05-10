package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ex20EditFriendActivity extends AppCompatActivity {

    EditText etName, etHp, etAddr,  etAge;

    TextView tvSex;

    String selectSex = "남자";

    Button btnFind,btnEdit;

    Switch swSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex20_edit_friend);



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
            public void onClick(View view)
            {

                String findName = etName.getText().toString();
                if(findName.equals(""))
                {
                    Toast.makeText(Ex20EditFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0; i<adapter.getCount();i++)
                {

                    if(findName.equals(adapter.items.get(i).getName()))
                    {

                        etName.setText(adapter.items.get(i).getName());
                        etHp.setText(adapter.items.get(i).getHp());
                        etAddr.setText(adapter.items.get(i).getAddr());
                        etAge.setText(adapter.items.get(i).getAge()+"");
                        if("남자".equals(adapter.items.get(i).getSex()))
                        {
                            swSex.setChecked(false);
                        }
                        else {
                            swSex.setChecked(true);
                        }
                    }
                }


            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                String findName = etName.getText().toString();

                if(findName.equals(""))
                {
                    Toast.makeText(Ex20EditFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex20EditFriendActivity.this, "연락처를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex20EditFriendActivity.this, "주소를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(findName.equals(""))
                {
                    Toast.makeText(Ex20EditFriendActivity.this, "나이를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = etName.getText().toString();
                String hp = etHp.getText().toString();
                String addr = etAddr.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                //리스트뷰 어댑터에 처리하는부분...
                for(int i=0; i<adapter.getCount();i++)
                {

                    if(findName.equals(adapter.items.get(i).getName()))
                    {

                        adapter.items.get(i).setName(name);
                        adapter.items.get(i).setHp(hp);
                        adapter.items.get(i).setAddr(addr);
                        adapter.items.get(i).setAge(age+"");
                        if("남자".equals(adapter.items.get(i).getSex()))
                        {
                            adapter.items.get(i).setSex(selectSex);
                        }
                        else {
                            adapter.items.get(i).setSex(selectSex);
                        }
                    }
                    Toast.makeText(Ex20EditFriendActivity.this, "수정완료!", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etHp.setText("");
                    etAddr.setText("");
                    etAge.setText("");
                }
                //메인에 있는 리스트뷰에 변화가 생겨서 갱신.
                adapter.notifyDataSetChanged();
            }
        });
    }
}