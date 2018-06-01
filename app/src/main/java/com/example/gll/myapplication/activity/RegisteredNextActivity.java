package com.example.gll.myapplication.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.Base2Activity;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mac on 2018/5/31.
 */

public class RegisteredNextActivity extends Base2Activity {
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_goto_home)
    TextView tvGotoHome;
    @BindView(R.id.tv_goto_artist)
    TextView tvGotoArtist;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_registered_next;
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.tv_goto_home, R.id.tv_goto_artist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_goto_home:
                startActivity(MainActivity.class);
                break;
            case R.id.tv_goto_artist:
                startActivity(ArtistProfileSubmitActivity.class);
                break;
        }
    }
}
