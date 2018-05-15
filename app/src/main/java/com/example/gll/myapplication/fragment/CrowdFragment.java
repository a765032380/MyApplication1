package com.example.gll.myapplication.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseFragment;


/**
 * 众筹页面
 */

public class CrowdFragment extends BaseFragment {
    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_crowd;
    }
    public static CrowdFragment newInstance() {
        return new CrowdFragment();
    }
}
