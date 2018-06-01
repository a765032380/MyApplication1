package com.example.gll.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mac on 2018/5/30.
 */

public class BindPhoneActivity extends BaseActivity {

    private String getCodeUrl="";
    private String submitUrl="";

    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.tv_obtain)
    TextView tv_obtain;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private String phone;
    private String code;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.tv_return, R.id.tv_obtain, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_obtain:
                obtain();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        phone=ed_phone.getText().toString();
        code=ed_code.getText().toString();

    }
    private void obtain(){
        code=ed_code.getText().toString();

    }
}
