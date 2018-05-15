package com.example.gll.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.HomeActorSelectAdapter;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.bean.ShareCardItem;
import com.example.gll.myapplication.loader.GlideImageLoader;
import com.example.gll.myapplication.util.StreamUtils;
import com.example.gll.myapplication.view.card_view.ShareCardView;
import com.google.gson.Gson;
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
//    Banner banner;
    ShareCardView shareCardView;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.rv_home_content);
//        banner=view.findViewById(R.id.banner_artist);
//        setBanner();
        shareCardView = view.findViewById(R.id.share_cardview);
        loadData();
        HomeActorSelectAdapter actorSelectAdapter=new HomeActorSelectAdapter(getData());
        setRecycleAdapter(actorSelectAdapter);
    }
    private void loadData() {
        List imageList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShareCardItem.LRCardItem lrCardItem1 = new ShareCardItem.LRCardItem();
            lrCardItem1.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
            lrCardItem1.setTitle("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
            imageList.add(lrCardItem1);
        }
        ShareCardItem item = new ShareCardItem();
        ShareCardItem.LRCardItem lrCardItem2=new ShareCardItem.LRCardItem();
        lrCardItem2.setContent("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        imageList.add(lrCardItem2);
        ShareCardItem.LRCardItem lrCardItem3=new ShareCardItem.LRCardItem();
        lrCardItem3.setContent("http://www.taopic.com/uploads/allimg/111011/2893-11101109325830.jpg");
        imageList.add(lrCardItem3);
        ShareCardItem.LRCardItem lrCardItem4=new ShareCardItem.LRCardItem();
        lrCardItem4.setContent("http://www.taopic.com/uploads/allimg/111011/2893-11101109325830.jpg");
        imageList.add(lrCardItem4);
        ShareCardItem.LRCardItem lrCardItem5=new ShareCardItem.LRCardItem();
        lrCardItem5.setContent("http://www.taopic.com/uploads/allimg/111011/2893-11101109325830.jpg");
        imageList.add(lrCardItem5);


        item.setDataList(imageList);
        shareCardView.setCardData(item);
        shareCardView.setOnClickListener(new ShareCardView.OnClickListener1() {
            @Override
            public void onClickListener(String url) {
//                Intent intent = new Intent(getContext(), MyWebViewActivity.class);
//                intent.putExtra("url", url);
//                getContext().startActivity(intent);
            }
        });


//        String json = StreamUtils.get(getContext(), R.raw.data);
//        ShareCardItem item = new Gson().fromJson(json, ShareCardItem.class);
//        shareCardView.setCardData(item);
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
//    private void setBanner() {
//        banner.setImageLoader(new GlideImageLoader());
//        //设置图片集合
//        List<String> list = new ArrayList<>();
//        //网络图片
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
//        list.add("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
//        banner.setImages(list);
//        //设置轮播时间
//        banner.setDelayTime(1500);
//        //banner设置方法全部调用完毕时最后调用
//        banner.start();
//    }
}
