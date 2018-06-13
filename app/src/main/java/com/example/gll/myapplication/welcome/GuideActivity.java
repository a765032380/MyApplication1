package com.example.gll.myapplication.welcome;

/**
 * 引导页
 * Created by Administrator on 2017-09-27.
 */


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.gll.myapplication.R;
import com.example.gll.myapplication.activity.LoginActivity;
import com.example.gll.myapplication.activity.MainActivity;
import com.example.gll.myapplication.base.Base2Activity;
import com.example.gll.myapplication.base.BaseActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * app欢迎界面的图片滑动界面
 *
 * @author 001
 */
public class GuideActivity extends Base2Activity implements OnClickListener,
        OnPageChangeListener {

    private ViewPager viewPager;
    ViewPagerAdapter vpAdapter;
    private List<View> views;
    private TextView button;


    // 引导图片资源
    private static final int[] pics = {R.drawable.guidance_01,
            R.drawable.guidance_01, R.drawable.guidance_01,
            R.drawable.guidance_01};


    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_guide;
    }

    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    // 当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        if (arg0 == 3) {
            button.setVisibility(View.VISIBLE);
        } else if (arg0 == 2) {
            button.setVisibility(View.GONE);
        } else if (arg0 == 1) {
            button.setVisibility(View.GONE);
        } else if (arg0 == 0) {
            button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
                // 结束掉当前的activity
                GuideActivity.this.finish();
                break;
            default:
                break;
        }
    }



    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    @Override
    protected void initUI() {
        views = new ArrayList<View>();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageBitmap(readBitMap(this, pics[i]));
            views.add(imageView);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        button= (TextView) findViewById(R.id.button);
        button.setOnClickListener(this);
        // 初始化Adapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(viewPagerAdapter);
        // 绑定回调
        viewPager.setOnPageChangeListener(this);
        button = (TextView) findViewById(R.id.button);
    }

    @Override
    protected void initData() {

    }
}
