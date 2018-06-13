package com.example.gll.myapplication.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.base.CommonAdapter;
import com.example.gll.myapplication.adapter.base.ViewHolder;
import com.example.gll.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gll.myapplication.util.Text.getData;

/**
 * Created by mac on 2018/6/8.
 * 众筹详情页面
 */

public class CrowdDetailsActivity extends BaseActivity implements OnProgressBarListener {
    /**
     * UI
     */

    @BindView(R.id.tv_return)
    TextView tv_return;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_button1)
    TextView tv_button1;
    @BindView(R.id.tv_button2)
    TextView tv_button2;
    @BindView(R.id.mNumberProgressBar)
    NumberProgressBar mNumberProgressBar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_context)
    TextView tv_context;
    @BindView(R.id.tv_investment)
            TextView tv_investment;

    CommonAdapter adapter;




    @OnClick({R.id.tv_return,R.id.tv_button1,R.id.tv_button2,R.id.tv_investment})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_button1:
                //收藏
                collection();
                break;
            case R.id.tv_button2:
                //分享
                share();
                break;
                //我要拍电影
            case R.id.tv_investment:
                startActivity(InvestmentActivity.class);
                break;
        }
    }
    private void setRecyclerView(){
        adapter=new CommonAdapter<String>(this,R.layout.item_crowd_details, getData()) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setRoundByXfermode(R.id.iv_image,R.drawable.ic_launcher_background);
                holder.setText(R.id.tv_name1,s);
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void collection() {

    }

    private void share() {

    }


    @Override
    protected void initUI() {
        ButterKnife.bind(this);

        mNumberProgressBar.setOnProgressBarListener(this);
        mNumberProgressBar.setProgress(100);
        setRecyclerView();
        tv_context.setText(Html.fromHtml("<h1>你好</h1>" +
                "<h6>你好</h6>"));
        //设置定时器
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //设置当前进度条的位置
////                        bnp.setProgress(5);
//                        //ProgressBar进度+1
//                        mNumberProgressBar.incrementProgressBy(1);
//                    }
//                });
//            }
//        }, 1000, 100);
    }



    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_crowd_details;
    }

    @Override
    public void onProgressChange(int current, int max) {
//        if (current==max) {
//            mNumberProgressBar.setProgress(0);
//        }
    }
}
