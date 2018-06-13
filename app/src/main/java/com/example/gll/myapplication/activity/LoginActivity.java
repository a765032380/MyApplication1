package com.example.gll.myapplication.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestCenter;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.Base2Activity;
import com.example.gll.myapplication.bean.Login;
import com.example.gll.myapplication.constant.URL;
import com.example.gll.myapplication.util.ParamsUtils;
import com.example.gll.myapplication.util.RegexUtils;
import com.example.gll.myapplication.util.RequestUtils;
import com.example.gll.myapplication.util.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Base2Activity {

    public static LoginActivity loginActivity;
    @BindView(R.id.iv_title)
    ImageView iv_title;
    @BindView(R.id.ed_account)
    EditText ed_account;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.tv_hidden)
    TextView tv_hidden;
    @BindView(R.id.tv_forgot_password)
    TextView tv_forgot_password;
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_goto_registered)
    TextView tv_goto_registered;
    @BindView(R.id.tv_WaChat)
    TextView tv_WaChat;
    @BindView(R.id.tv_QQ)
    TextView tv_QQ;
    private boolean ishidden=true;

    @Override
    protected void initUI() {
        if (SPUtil.isLogin()){
            startActivity(MainActivity.class);
            finish();
        }
        ButterKnife.bind(this);
        loginActivity=this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.tv_hidden, R.id.tv_forgot_password, R.id.tv_login, R.id.tv_goto_registered, R.id.tv_WaChat, R.id.tv_QQ})
    public void onClick(View view) {
        switch (view.getId()) {
            //隐藏或者显示密码
            case R.id.tv_hidden:
                hidden();
                break;
                //忘记密码
            case R.id.tv_forgot_password:
                forgotPassword();
                break;
                //登录
            case R.id.tv_login:
                login();
                break;
                //去注册
            case R.id.tv_goto_registered:
                startActivity(RegisteredActivity.class);
                break;
                //微信登录
            case R.id.tv_WaChat:
                goWaChat();
                break;
                //QQ登录
            case R.id.tv_QQ:
                goQQ();
                break;
        }
    }

    private void goQQ() {

    }

    private void goWaChat() {

    }

    private void login() {
        String phone=ed_account.getText().toString();
        String password=ed_password.getText().toString();
        if (!RegexUtils.checkMobile(phone)){
            showToast("请输入正确的手机号");
            return;
        }
        if (!RegexUtils.checkPassword(password)){
            showToast("密码必须8—16位");
            return;
        }

        RequestParams params=new RequestParams();
        params.put("mobile",phone);
        params.put("password",password);
        params.put("time","12345646");
        params.put("base","12345651269");

        RequestCenter.postRequest(URL.API_LOGIN, Login.class, ParamsUtils.addRow(params), new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SPUtil.isLogin(true);
                startActivity(MainActivity.class);
                finish();
//                Login login= (Login) responseObj;
//                if (login.getCode()== RequestUtils.SUCCESS){
//                    loginSuccess(login);
//                    finish();
//                }else {
//
//                }
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });

    }

    private void loginSuccess(Login login) {

    }

    private void forgotPassword() {
        startActivity(VerifyPhoneActivity.class);
    }

    private void hidden() {
        showToast("你点击了隐藏按钮");
        if (ishidden) {
            ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ed_password.setSelection(ed_password.getText().length());
            ishidden=false;
        }else {
            ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ed_password.setSelection(ed_password.getText().length());
            ishidden=true;
        }
    }
}
