package com.example.gll.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gll.myapplication.app.MyApp;
import com.example.gll.myapplication.manager.SPManager;

/**
 * SharedPreferences工具类
 * Created by Administrator on 2017-09-27.
 */

public class SPUtil {

    /**
     * 网络请求时的cookie
     */
    public static String KEY_JSESSION_ID = "access_token";


    public static SharedPreferences getSharedPreferences(String key) {
        return MyApp.getContent().getSharedPreferences(key, Context.MODE_PRIVATE);
    }


    /**
     * 获取cookie令牌
     *
     * @param str
     * @return
     */
    public static String getCookieStr(String str) {
        return getSharedPreferences(KEY_JSESSION_ID).getString(str, "");
    }

    /**
     * 保存cookie令牌
     *
     * @param str
     */
    public static void saveCookieStr(String str) {
        SharedPreferences sp = getSharedPreferences(KEY_JSESSION_ID);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_JSESSION_ID, str);
        editor.commit();
    }
    public static boolean isLogin(){
        return SPManager.getInstance().getBoolean("isLogin",false);
    }
    public static void isLogin(boolean isLogin){

         SPManager.getInstance().putBoolean("isLogin",isLogin);
    }



    /**
     * 获取sp中保存的更新次数（与当前更新次数判断是否第一次进入）
     *
     * @return
     */
    public static int isFirstLogin() {
        SharedPreferences sp = getSharedPreferences("firstLogin");
        return sp.getInt("firstLogin", 0);
    }

    /**
     * 将当前更新次数保存在sp中
     *
     * @param isFirst
     */
    public static void setFirstLogin(int isFirst) {
        SharedPreferences sp = getSharedPreferences("firstLogin");
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("firstLogin", isFirst);
        editor.commit();
    }

    /**
     * 登录后储存账户信息
     */
//    public static void setUserInfo(LoginBean userInfo) {
//        SharedPreferences sp = getSharedPreferences("userinfo");
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("loginBean", new Gson().toJson(userInfo));
//        editor.commit();
//    }

    /**
     * 获取账户信息
     */
//    public static LoginBean getUserInfo() {
//        SharedPreferences sp = getSharedPreferences("userinfo");
//        String loginBean = sp.getString("loginBean", "");
//        if (CacheUtil.isBank(loginBean)) {
//            return new LoginBean();
//        }
//        return new Gson().fromJson(loginBean, LoginBean.class);
//    }


    /**
     * 获取sp中极光是否打开
     *
     * @return
     */
    public static boolean isJpushOpen() {
        SharedPreferences sp = getSharedPreferences("isJpushOpen");
        return sp.getBoolean("isopen", true);
    }

    /**
     * 将极光推送保存在sp中
     */
    public static void setJpushOpen(boolean isopen) {
        SharedPreferences sp = getSharedPreferences("isJpushOpen");
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isopen", isopen);
        editor.commit();
    }

    /**
     * 获取sp中通知数量
     *
     * @return
     */
    public static int getNoticeNum() {
        SharedPreferences sp = getSharedPreferences("noticeNum");
        return sp.getInt("noticeNum", 0);
    }

    /**
     * 将极通知数量保存在sp中
     */
    public static void setNoticeNum(int noticeNum) {
        SharedPreferences sp = getSharedPreferences("noticeNum");
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("noticeNum", noticeNum);
        editor.commit();
    }

}
