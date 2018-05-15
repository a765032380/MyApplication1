package com.example.gll.myapplication.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.HomeActorSelectAdapter;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.loader.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;


/**
 * 艺人的界面
 */

public class ArtistFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artist;
    }
    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }
    RecyclerView recyclerView;
    Banner banner;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.rv_home_content);
        banner=view.findViewById(R.id.banner_artist);
        setBanner();
        HomeActorSelectAdapter actorSelectAdapter=new HomeActorSelectAdapter(getData());
        setRecycleAdapter(actorSelectAdapter);
    }
    private void setRecycleAdapter(RecyclerView.Adapter adapter){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<String> getData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add("测试数据"+i);
        }
        return mList;
    }
    private void setBanner() {
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> list = new ArrayList<>();
        //网络图片
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
        banner.setImages(list);
        //设置轮播时间
        banner.setDelayTime(1500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
