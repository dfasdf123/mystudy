package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Ex17NumberListActivity extends AppCompatActivity {

    TextView tvName1, tvName2, tvName3, tvName4, tvName5;
    TextView tvNum1, tvNum2, tvNum3, tvNum4, tvNum5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex17_number_list);

        tvName1 = (TextView) findViewById(R.id.tvName1);
        tvName2 = (TextView) findViewById(R.id.tvName2);
        tvName3 = (TextView) findViewById(R.id.tvName3);
        tvName4 = (TextView) findViewById(R.id.tvName4);
        tvName5 = (TextView) findViewById(R.id.tvName5);
        tvNum1 = (TextView) findViewById(R.id.tvNum1);
        tvNum2 = (TextView) findViewById(R.id.tvNum2);
        tvNum3 = (TextView) findViewById(R.id.tvNum3);
        tvNum4 = (TextView) findViewById(R.id.tvNum4);
        tvNum5 = (TextView) findViewById(R.id.tvNum5);

        findViewById(R.id.ivPhone1).setOnClickListener(mClick);
        findViewById(R.id.ivPhone2).setOnClickListener(mClick);
        findViewById(R.id.ivPhone3).setOnClickListener(mClick);
        findViewById(R.id.ivPhone4).setOnClickListener(mClick);
        findViewById(R.id.ivPhone5).setOnClickListener(mClick);
    }
    View.OnClickListener mClick = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.ivPhone1:
                    String tel_name1 = "tel:"+tvName1.getText().toString();
                    String tel_number1 = "tel:"+tvNum1.getText().toString();

                    Intent intentTel1 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number1));
                    startActivity(intentTel1);
                    Toast.makeText(Ex17NumberListActivity.this,tel_name1+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivPhone2:
                    String tel_name2 = "tel:"+tvName2.getText().toString();
                    String tel_number2 = "tel:"+tvNum2.getText().toString();

                    Intent intentTel2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number2));
                    startActivity(intentTel2);
                    Toast.makeText(Ex17NumberListActivity.this,tel_name2+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivPhone3:
                    String tel_name3 = "tel:"+tvName3.getText().toString();
                    String tel_number3 = "tel:"+tvNum3.getText().toString();

                    Intent intentTel3 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number3));
                    startActivity(intentTel3);
                    Toast.makeText(Ex17NumberListActivity.this,tel_name3+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivPhone4:
                    String tel_name4 = "tel:"+tvName4.getText().toString();
                    String tel_number4 = "tel:"+tvNum4.getText().toString();

                    Intent intentTel4 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number4));
                    startActivity(intentTel4);
                    Toast.makeText(Ex17NumberListActivity.this,tel_name4+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivPhone5:
                    String tel_name5 = "tel:"+tvName5.getText().toString();
                    String tel_number5 = "tel:"+tvNum5.getText().toString();

                    Intent intentTel5 = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number5));
                    startActivity(intentTel5);
                    Toast.makeText(Ex17NumberListActivity.this,tel_name5+"에게 전화를 겁니다!",Toast.LENGTH_SHORT).show();
                    break;

            }

        }
    };
}