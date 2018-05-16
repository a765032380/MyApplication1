package com.example.gll.myapplication.activity.personal;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置
 */

public class SettingActivity extends BaseActivity {

//    @BindView(R.id.tv_return)
//    TextView tv_return;
//    @BindView(R.id.ll_about)
//    LinearLayout ll_about;
//    @BindView(R.id.ll_license)
//    LinearLayout ll_license;
//    @BindView(R.id.ll_privacy)
//    LinearLayout ll_privacy;
//    @BindView(R.id.ll_version)
//    LinearLayout ll_version;
//    @OnClick({R.id.tv_return,
//            R.id.ll_about,
//            R.id.ll_license,
//            R.id.ll_privacy,
//            R.id.ll_version})
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.tv_return:
//                break;
//            case R.id.ll_about:
//                break;
//            case R.id.ll_license:
//                break;
//            case R.id.ll_privacy:
//                break;
//            case R.id.ll_version:
//                break;
//        }
//    }




    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }
}
