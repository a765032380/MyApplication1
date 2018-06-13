package com.example.gll.myapplication.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.activity.personal.AccountActivity;
import com.example.gll.myapplication.activity.personal.CachingActivity;
import com.example.gll.myapplication.activity.personal.CollectionActivity;
import com.example.gll.myapplication.activity.personal.HelpActivity;
import com.example.gll.myapplication.activity.personal.HistoryActivity;
import com.example.gll.myapplication.activity.personal.PasswordActivity;
import com.example.gll.myapplication.activity.personal.SettingActivity;
import com.example.gll.myapplication.activity.personal.VoteActivity;
import com.example.gll.myapplication.activity.personal.WalletActivity;
import com.example.gll.myapplication.adapter.HomeAdapter;
import com.example.gll.myapplication.adapter.base.CommonAdapter;
import com.example.gll.myapplication.adapter.base.ViewHolder;
import com.example.gll.myapplication.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.gll.myapplication.util.Text.getData;


/**
 * 个人中心页面
 */

/***
 离线缓存  caching
 观看历史	history
 我的收藏	collection
 我的钱包	wallet
 我的投票  vote
 账户与安全account
 修改密码	password
 设置	setting
 帮助反馈  help
 */


public class PersonalFragment extends BaseFragment {
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }
    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }
//    @BindView(R.id.ll_caching)
//    LinearLayout ll_caching;
//    @BindView(R.id.ll_history)
//    LinearLayout ll_history;
//    @BindView(R.id.ll_collection)
//    LinearLayout ll_collection;
//    @BindView(R.id.ll_wallet)
//    LinearLayout ll_wallet;
//    @BindView(R.id.ll_vote)
//    LinearLayout ll_vote;
//    @BindView(R.id.ll_account)
//    LinearLayout ll_account;
//    @BindView(R.id.ll_password)
//    LinearLayout ll_password;
//    @BindView(R.id.ll_setting)
//    LinearLayout ll_setting;
//    @BindView(R.id.ll_help)
//    LinearLayout ll_help;
//
//    @OnClick({R.id.ll_caching,
//            R.id.ll_history,
//            R.id.ll_collection,
//            R.id.ll_wallet,
//            R.id.ll_vote,
//            R.id.ll_account,
//            R.id.ll_password,
//            R.id.ll_setting,
//            R.id.ll_help,
//    })
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.ll_caching:
//                startActivity(CachingActivity.class);
//                break;
//            case R.id.ll_history:
//                startActivity(HistoryActivity.class);
//                break;
//            case R.id.ll_collection:
//                startActivity(CollectionActivity.class);
//                break;
//            case R.id.ll_wallet:
//                startActivity(WalletActivity.class);
//                break;
//            case R.id.ll_vote:
//                startActivity(VoteActivity.class);
//                break;
//            case R.id.ll_account:
//                startActivity(AccountActivity.class);
//                break;
//            case R.id.ll_password:
//                startActivity(PasswordActivity.class);
//                break;
//            case R.id.ll_setting:
//                startActivity(SettingActivity.class);
//                break;
//            case R.id.ll_help:
//                startActivity(HelpActivity.class);
//                break;
//        }
//    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ButterKnife.bind(this,view);
        initRecycleView();
    }
    private void initRecycleView() {
        mRecyclerView.setAdapter(new CommonAdapter<String>(getContext(),R.layout.item_personal, getData()) {
            @Override
            public void convert(ViewHolder holder, final String s) {
                holder.setText(R.id.tv_context,s);
                holder.setOnClickListener(R.id.ll_personal_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("点击了"+s);
                    }
                });
//                holder.setImageResource(R.id.iv_image,s)
            }

        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setFocusable(false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

}
