package com.example.gll.myapplication.util;

import com.baisi.myapplication.adutil.Base64;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.google.gson.Gson;

/**
 * Created by mac on 2018/6/1.
 */

public class ParamsUtils {

    public static String toJson(RequestParams params){
        Gson gson=new Gson();
        return Base64.encode(gson.toJson(params));
    }
    public static RequestParams addRow(RequestParams params){
        RequestParams params1=new RequestParams();
        params1.put("row", toJson(params));
        return params1;
    }
}
