package com.example.gll.myapplication.activity;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by mac on 2018/6/11.
 * 我要拍电影页面
 */

public class InvestmentActivity extends BaseActivity {
    @Override
    protected void initUI() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_investment;
    }
}
