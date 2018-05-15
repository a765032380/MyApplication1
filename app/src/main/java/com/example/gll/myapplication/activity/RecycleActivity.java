package com.example.gll.myapplication.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.HomeAdapter;
import com.example.gll.myapplication.base.BaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleActivity extends BaseActivity implements OnRefreshListener,OnLoadMoreListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private ArrayList<String> mList=new ArrayList<>();;
    private HomeAdapter adapter;

    @Override
    protected void initUI() {
        ButterKnife.bind(this);

        adapter=new HomeAdapter( getData() );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

    }

    @Override
    protected void initData() {
//        mList=new ArrayList<>();
//        for (int i=0;i<100;i++){
//            mList.add("测试数据"+i);
//        }
    }
    private ArrayList<String> getData(){
        for (int i=0;i<100;i++){
            mList.add("测试数据"+i);
        }
        return mList;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_recycleview;
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        adapter.updateData(getData());
        refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
    }
}
