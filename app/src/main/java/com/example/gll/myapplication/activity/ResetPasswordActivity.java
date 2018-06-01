package com.example.gll.myapplication.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

public class ResetPasswordActivity extends BaseActivity {
    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.tv_hidden)
    TextView tv_hidden;
    @BindView(R.id.ed_password_again)
    EditText ed_password_again;
    @BindView(R.id.tv_hidden_again)
    TextView tv_hidden_again;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private boolean isHidden=true;
    private boolean isHiddenAgain=true;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_return, R.id.tv_hidden, R.id.tv_hidden_again, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_hidden:
                hidden();
                break;
            case R.id.tv_hidden_again:
                hiddenAgain();
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        finish();
        startActivity(LoginActivity.class);
    }

    private void hiddenAgain() {
        if (isHiddenAgain) {
            ed_password_again.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ed_password_again.setSelection(ed_password_again.getText().length());
            isHiddenAgain=false;
        }else {
            ed_password_again.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ed_password_again.setSelection(ed_password_again.getText().length());
            isHiddenAgain=true;
        }
    }

    private void hidden() {
        if (isHidden) {
            ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ed_password.setSelection(ed_password.getText().length());
            isHidden=false;
        }else {
            ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ed_password.setSelection(ed_password.getText().length());
            isHidden=true;
        }
    }
}
