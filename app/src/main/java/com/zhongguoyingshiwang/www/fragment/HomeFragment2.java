package com.zhongguoyingshiwang.www.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.zhongguoyingshiwang.www.R;
import com.zhongguoyingshiwang.www.base.BaseFragment;
import com.zhongguoyingshiwang.www.home_title.XiamiLayout;
import com.zhongguoyingshiwang.www.home_title.XiamiPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 480*375
 * 96*75
 * 32*25
 */

public class HomeFragment2 extends BaseFragment{
    public static HomeFragment2 newInstance() {
        return new HomeFragment2();
    }
    private XiamiLayout xiamiLayout;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        xiamiLayout = view.findViewById(R.id.xiamilayout);
        init(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }
    private void init(View view){
        List<Fragment> views = new ArrayList<>();
        Fragment home = HomeFragment.newInstance();
        Fragment viwe2 = HomeFragment.newInstance();
        Fragment viwe3 = HomeFragment.newInstance();
        Fragment viwe4 = HomeFragment.newInstance();
        views.add(home);
        views.add(viwe2);
        views.add(viwe3);
        views.add(viwe4);
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("城市故事");
        titles.add("健康养生");
        titles.add("城市文化");
        XiamiPagerAdapter xiamiPagerAdapter = new XiamiPagerAdapter(getFragmentManager(),views, titles);

        xiamiLayout.setAdapter(xiamiPagerAdapter,0);
    }

}
