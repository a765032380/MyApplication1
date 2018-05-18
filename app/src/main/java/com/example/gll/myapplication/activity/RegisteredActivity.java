package com.example.gll.myapplication.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisteredActivity extends BaseActivity {

    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.ed_password_one)
    EditText ed_password_one;
    @BindView(R.id.ed_password_twe)
    EditText ed_password_twe;
    @BindView(R.id.tv_obtain)
    TextView tv_obtain;
    @BindView(R.id.tv_registered)
    TextView tv_registered;
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;
    @BindView(R.id.iv_title)
    ImageView iv_title;

    @OnClick({R.id.iv_title,
            R.id.tv_agreement,
            R.id.tv_registered,
            R.id.tv_obtain})
    public void onClick(View view){
        switch (view.getId()){
            //头部图片
            case R.id.iv_title:

                break;
                //用户注册协议
            case R.id.tv_agreement:

                break;
                //注册按钮
            case R.id.tv_registered:

                break;
                //获取验证码
            case R.id.tv_obtain:

                break;
        }
    }





    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_registered;
    }
}
