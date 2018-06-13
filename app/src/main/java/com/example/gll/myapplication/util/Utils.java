package com.example.gll.myapplication.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import com.example.gll.myapplication.activity.LoginActivity;

import java.util.List;

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
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

}

