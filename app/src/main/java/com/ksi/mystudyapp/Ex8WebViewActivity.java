package com.ksi.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Ex8WebViewActivity extends AppCompatActivity {

    EditText etInputAddr;

    Button btnMove1, btnMove2;

    String[] urlItems = {"네이버","다음","유튜브","구글"};
    String[] urlGo = {"https://naver.com","https://daum.net","https://youtube.com","https://google.com"};

    int urlIdx=0; //스피너 선택 포지션

    WebView webView;

    TextView tvSite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex8_webview);

        etInputAddr = (EditText) findViewById(R.id.etInputAddr);
        btnMove1 = (Button) findViewById(R.id.btnmove1);
        btnMove2 = (Button) findViewById(R.id.btnMove2);
        webView = (WebView) findViewById(R.id.webView);
        tvSite = (TextView) findViewById(R.id.tvSite);


        findViewById(R.id.btnmove1).setOnClickListener(mClickListener);
        findViewById(R.id.btnMove2).setOnClickListener(mClickListener);


        //웹뷰 옵션 설정하기. ==========================================
        webView.setWebViewClient(new WebViewClient());  // 새 창 띄우기 않기
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true); // 자바스크립트 사용여부
        webView.loadUrl("https://naver.com");

        //웹뷰 ==========================================

        //스피너 처리 =========================================
        Spinner spinner = (Spinner) findViewById(R.id.spSite);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, urlItems
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 스피너에 어댑터 설정
        spinner.setAdapter(adapter);

        // 스피너에서 선택 했을 경우 이벤트 처리
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvSite.setText(urlItems[position]);
                urlIdx = position; //선택한 url에 idx값 넣기
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvSite.setText("선택대기중...");

            }
        });
    }
    //스피너 처리 =========================================

    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnmove1:
                    String url = etInputAddr.getText().toString();
                    webView.loadUrl("https://"+url);
                    break;
                case R.id.btnMove2:
                    webView.loadUrl(urlGo[urlIdx]);
                    break;

            }
        }
    };
}