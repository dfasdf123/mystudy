package com.ksi.mystudyapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Ex30ServerRequestJoin extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://blacksutte12.dothome.co.kr/test/join.php";
    private Map<String, String> map;


    public Ex30ServerRequestJoin(String id, String pw, String name, String hp, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("id",id);
        map.put("pw",pw);
        map.put("name",name);
        map.put("hp",hp);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
