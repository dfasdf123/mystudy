package com.ksi.mystudyapp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Ex30ServerRequestEdit extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )


    final static private String URL = "http://blacksutte12.dothome.co.kr/test/edit.php";
    private Map<String, String> map;


    public Ex30ServerRequestEdit(String eidtId, String eidtName, String eidtHp, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("eidtId",eidtId);
        map.put("eidtName",eidtName);
        map.put("eidtHp",eidtHp);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
