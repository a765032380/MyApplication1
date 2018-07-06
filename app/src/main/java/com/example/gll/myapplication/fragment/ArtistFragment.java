package com.example.gll.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.activity.video.SkinActivity;
import com.example.gll.myapplication.adapter.HomeActorSelectAdapter;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.bean.ShareCardItem;
import com.example.gll.myapplication.view.card_view.ShareCardView;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 艺人的界面
 */

public class ArtistFragment extends BaseFragment implements OnRefreshListener{
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_artist;
    }
    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }
    @BindView(R.id.rv_home_content)
    RecyclerView recyclerView;
    @BindView(R.id.share_cardview)
    ShareCardView shareCardView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        loadData();
        setRefreshLayout();
        HomeActorSelectAdapter actorSelectAdapter=new HomeActorSelectAdapter(getData());
        setRecycleAdapter(actorSelectAdapter);
    }

    private void setRefreshLayout() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.setEnableLoadMore(false);
    }

    private void loadData() {
        List imageList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            ShareCardItem.LRCardItem lrCardItem1 = new ShareCardItem.LRCardItem();
//            lrCardItem1.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
//            lrCardItem1.setTitle("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
//            imageList.add(lrCardItem1);
//        }
        ShareCardItem item = new ShareCardItem();
        ShareCardItem.LRCardItem lrCardItem2=new ShareCardItem.LRCardItem();
        lrCardItem2.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg");
        imageList.add(lrCardItem2);
        ShareCardItem.LRCardItem lrCardItem3=new ShareCardItem.LRCardItem();
        lrCardItem3.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgdmpxi7erj20qy0qyjtr.jpg");
        imageList.add(lrCardItem3);
        ShareCardItem.LRCardItem lrCardItem4=new ShareCardItem.LRCardItem();
        lrCardItem4.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
        imageList.add(lrCardItem4);
        ShareCardItem.LRCardItem lrCardItem5=new ShareCardItem.LRCardItem();
        lrCardItem5.setContent("https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg");
        imageList.add(lrCardItem5);



        item.setDataList(imageList);
        shareCardView.setCardData(item);
        shareCardView.setOnClickListener(new ShareCardView.OnClickListener1() {
            @Override
            public void onClickListener(String url) {

                startActivity(SkinActivity.class);
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

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
    }
}
