package com.example.gll.myapplication.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.IdiomStoryAdapter;
import com.example.gll.myapplication.base.BaseActivity;
import com.example.gll.myapplication.loader.GlideImageLoader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gll.myapplication.util.Text.getData;

/**
 * 成语故事
 */
public class IdiomStoryActivity extends BaseActivity implements OnRefreshListener,OnLoadMoreListener{
    @Override
    protected int getContentViewId() {
        return R.layout.activity_idiom_story;
    }

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.tv_return)
    TextView tv_return;
    @OnClick(R.id.tv_return)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_return:
                finish();
                break;
        }
    }

    private IdiomStoryAdapter adapter;
    @Override
    protected void initUI() {
        ButterKnife.bind(this);
        setBanner();
        initRefresh();
        initRecycleView();
    }

    @Override
    protected void initData() {

    }






    private void initRecycleView() {
        adapter=new IdiomStoryAdapter( getData() );
        recyclerView.setAdapter(adapter);
        GridLayoutManager linearLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setFocusable(false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
//        linearLayoutManager.setMeasurementCacheEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
    }
    private void initRefresh() {
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();//自动刷新
//        refreshLayout.setEnableLoadMore(false);     //关闭上拉加载更多
        refreshLayout.setOnLoadMoreListener(this);//上拉加载更多监听
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

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        ArrayList<String> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add("测试数据"+i);
        }

        adapter.updateData(list);
        refreshLayout.finishLoadMore(1000);
    }
}
