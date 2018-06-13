package com.example.gll.myapplication.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestCenter;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;
import com.example.gll.myapplication.bean.Registered;
import com.example.gll.myapplication.constant.URL;
import com.example.gll.myapplication.util.ParamsUtils;
import com.example.gll.myapplication.util.RegexUtils;
import com.example.gll.myapplication.util.RequestUtils;

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
    @BindView(R.id.tv_next)
    TextView tv_next;
    @BindView(R.id.tv_agreement)
    TextView tv_agreement;
    @BindView(R.id.iv_title)
    ImageView iv_title;
    @BindView(R.id.tv_ordinary)
    TextView tv_ordinary;
    @BindView(R.id.tv_artists)
    TextView tv_artists;
    private int type=0;


    @OnClick({R.id.iv_title,
            R.id.tv_agreement,
            R.id.tv_next,
            R.id.tv_obtain,
            R.id.tv_ordinary,
            R.id.tv_artists
    })
    public void onClick(View view) {
        switch (view.getId()) {
            //头部图片
            case R.id.iv_title:

                break;
            //用户注册协议
            case R.id.tv_agreement:
                startActivity(AgreementActivity.class);
                break;
            //注册按钮
            case R.id.tv_next:
                next();

                break;
            //获取验证码
            case R.id.tv_obtain:

                break;
            //选择注册成为普通会员
            case R.id.tv_ordinary:
                selectOrdinary();
                break;
            //选择注册成为艺人
            case R.id.tv_artists:
                selectArtists();
                break;
        }
    }
    @SuppressLint("ResourceAsColor")
    private void selectOrdinary() {
        type=0;
        tv_ordinary.setTextColor(R.color.colorPrimary);
        tv_artists.setTextColor(R.color.theme_color);
        tv_next.setText("注册");
    }
    @SuppressLint("ResourceAsColor")
    private void selectArtists() {
        type=1;
        tv_ordinary.setTextColor(R.color.theme_color);
        tv_artists.setTextColor(R.color.colorPrimary);
        tv_next.setText("下一步");
    }



    private void next() {
        if (type==1){
            //下一步
        }else {
            //进行注册请求
            String phone=ed_phone.getText().toString();
            String password=ed_password_one.getText().toString();
            String password_twe=ed_password_twe.getText().toString();
            String code=ed_code.getText().toString();
            if (!RegexUtils.checkMobile(phone)){
                showToast("请输入正确的手机号");
                return;
            }
            if (!RegexUtils.checkPassword(password)){
                showToast("密码必须8—16位");
                return;
            }
            if (!password.trim().equals(password_twe.trim())){
                showToast("两次密码输入不一致");
                return;
            }

            RequestParams params=new RequestParams();
            params.put("mobile",phone);
            params.put("password",password);
            params.put("code",code);
            params.put("time","12345646");
            params.put("base","12345651269");
            RequestCenter.postRequest(URL.API_REGISTERED, Registered.class, ParamsUtils.addRow(params), new DisposeDataListener() {
                @Override
                public void onSuccess(Object responseObj) {
                    Registered registered= (Registered) responseObj;
                    if (registered.getCode()== RequestUtils.SUCCESS){
                        finish();
                        LoginActivity.loginActivity.finish();
                        startActivity(RegisteredNextActivity.class);
                    }
                }

                @Override
                public void onFailure(Object reasonObj) {

                }
            });



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
