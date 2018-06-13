package com.example.gll.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.activity.CrowdDetailsActivity;
import com.example.gll.myapplication.adapter.HomeAdapter;
import com.example.gll.myapplication.adapter.base.CommonAdapter;
import com.example.gll.myapplication.adapter.base.ViewHolder;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.loader.GlideImageLoader;
import com.example.gll.myapplication.util.Text;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.gll.myapplication.util.Text.getData;


/**
 * 众筹页面
 */

public class CrowdFragment extends BaseFragment implements OnRefreshListener,OnLoadMoreListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_crowd;
    }
    public static CrowdFragment newInstance() {
        return new CrowdFragment();
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private CommonAdapter<String> adapter;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        initRefresh();
        initRecycleView();
    }
    private void initRecycleView() {
        adapter= new CommonAdapter<String>(getContext(),R.layout.item_rv_crowd, getData()) {
            @Override
            public void convert(ViewHolder holder, final String s) {
                holder.setText(R.id.tv_title,s);
                holder.setOnClickListener(R.id.ll_crowd_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击了"+s);
                        startActivity(CrowdDetailsActivity.class);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setFocusable(false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();//自动刷新
//        refreshLayout.setEnableLoadMore(false);     //关闭上拉加载更多
        refreshLayout.setOnLoadMoreListener(this);//上拉加载更多监听
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add("测试数据"+i);
        }

        adapter.updateData(list);
        refreshLayout.finishLoadMore(1100/*,false*/);//传入false表示加载失败
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1100/*,false*/);//传入false表示刷新失败
    }
}
