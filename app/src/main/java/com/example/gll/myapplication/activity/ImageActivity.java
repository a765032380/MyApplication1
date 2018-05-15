package com.example.gll.myapplication.activity;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.sunfusheng.glideimageview.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends BaseActivity {
    @BindView(R.id.mImageView)
    GlideImageView mImageView;
    @Override
    protected void initUI() {
        ButterKnife.bind(this);
        mImageView.loadImage("https://timgsa.baidu.com/timg?image&q" +
                "uality=80&size=b9999_10000&sec=1526293556251&di=80247daf9445641" +
                "c8defe9ea5c5cc52f&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0381de85949" +
                "053ca8012193a3339cc5.jpg",R.mipmap.ic_launcher);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_image;
    }
}
