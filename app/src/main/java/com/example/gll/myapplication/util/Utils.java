package com.example.gll.myapplication.util;

import android.content.Context;
import android.content.Intent;

import com.example.gll.myapplication.activity.LoginActivity;

/**
 * Created by mac on 2018/6/1.
 */

public class Utils {
    /**
     * 单点登录要用，清除栈内其他所有的Activity
     * @param context
     */
    public static void toLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
