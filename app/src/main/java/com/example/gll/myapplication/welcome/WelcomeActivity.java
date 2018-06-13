package com.example.gll.myapplication.welcome;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.activity.LoginActivity;
import com.example.gll.myapplication.activity.MainActivity;
import com.example.gll.myapplication.base.Base2Activity;
import com.example.gll.myapplication.base.BaseActivity;
import com.example.gll.myapplication.util.SPUtil;
import com.example.gll.myapplication.util.Utils;


/**
 * 启动页
 */
public class WelcomeActivity extends Base2Activity {

    /**
     * app更新次数
     */
    private int localVersion;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_welcome;
    }

    private void initPage() {
        try {
            localVersion = getVersionCode(this);
        } catch (Exception e) {
            e.printStackTrace();

        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {

                if (localVersion > SPUtil.isFirstLogin()) {
                    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                    WelcomeActivity.this.finish();
                    /**
                     * 保存当前更新次数
                     */
                    SPUtil.setFirstLogin(localVersion);
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                    WelcomeActivity.this.startActivity(intent);
                    WelcomeActivity.this.finish();
                }
//            }
//        }, 3000);


    }

    // 获取到当前程序的版本号
    private int getVersionCode(Context context) throws PackageManager.NameNotFoundException {
        int versionCode = 0;
        // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
        versionCode = context.getPackageManager().getPackageInfo(
                Utils.getAppProcessName(this), 0).versionCode;
        return versionCode;
    }

    @Override
    protected void initUI() {
        initPage();
    }

    @Override
    protected void initData() {

    }
}
