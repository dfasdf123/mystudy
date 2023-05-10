package com.ksi.mystudyapp;

import static com.ksi.mystudyapp.Ex20FriendListActivity.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Ex20AddFriendActivity extends AppCompatActivity {

    EditText etName, etHp, etAddr,  etAge;

    TextView tvSex;

    String selectSex = "남자";

    Switch swSex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex20_add_friend);



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
                        Toast.makeText(Ex20AddFriendActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(hp.equals(""))
                    {
                        Toast.makeText(Ex20AddFriendActivity.this, "연락처를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(addr.equals(""))
                    {
                        Toast.makeText(Ex20AddFriendActivity.this, "주소를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(age.equals(""))
                    {
                        Toast.makeText(Ex20AddFriendActivity.this, "나이를 입력하세요!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    adapter.addItem(new Ex20FriendItem(name,hp,sex,addr,age));
                    adapter.notifyDataSetChanged();
                    etName.setText("");
                    etHp.setText("");
                    etAddr.setText("");
                    etAge.setText("");
                    Toast.makeText(Ex20AddFriendActivity.this, "친구추가 완료!", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}