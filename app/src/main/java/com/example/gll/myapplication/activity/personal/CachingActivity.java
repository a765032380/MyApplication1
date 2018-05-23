package com.example.gll.myapplication.activity.personal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gll.myapplication.R;
import com.example.gll.myapplication.adapter.base.CommonAdapter;
import com.example.gll.myapplication.adapter.base.ViewHolder;
import com.example.gll.myapplication.base.BaseActivity;
import com.example.gll.myapplication.bean.TextClass;
import com.example.gll.myapplication.util.Text;
import com.example.gll.myapplication.util.download.Utils_Download;
import com.example.gll.myapplication.util.download.Utils_Parse;
import com.example.gll.myapplication.util.download.Utils_Toast;
import com.yaoxiaowen.download.DownloadConstant;
import com.yaoxiaowen.download.DownloadHelper;
import com.yaoxiaowen.download.DownloadStatus;
import com.yaoxiaowen.download.FileInfo;
import com.yaoxiaowen.download.utils.DebugUtils;

import java.io.File;
import java.util.List;

import static com.example.gll.myapplication.util.Text.getData;

/**
 * 离线缓存
 */

public class CachingActivity extends BaseActivity {

    public static final String TAG = "weny SimpleMainActivity";


    //淘宝 app 下载地址
    private static final String url = "http://imtt.dd.qq.com/16891/9A7CBD9CAFF7AA35E754408E2D2C6288.apk?fsname=com.tencent.mm_6.6.6_1300.apk&csr=1bbd";
    private static final String BC_ACTION = "download_helper_first_action";
    private static final String START = "开始";
    private static final String PAUSE = "暂停";
    RecyclerView recyclerView;
    ViewHolder holder;
    CommonAdapter adapter;
    List<TextClass> mList;
    int position;
    private File mFile;
    private String appName = "豌豆荚1.apk";
//    private int progress;
    private TextView textView;
    private ProgressBar progressBar;
    private TextView btn;
    private TextView deleteBtn;
    private DownloadHelper mDownloadHelper;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent) {
                switch (intent.getAction()) {
                    case BC_ACTION:{
                        /**
                         * 我们接收到的FileInfo对象，包含了下载文件的各种信息。
                         * 然后我们就可以做我们想做的事情了。
                         * 比如更新进度条，改变状态等。
                         */
                        FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(DownloadConstant.EXTRA_INTENT_DOWNLOAD);

                        float pro = (float) (fileInfo.getDownloadLocation()*1.0/ fileInfo.getSize());
                        int progress = (int)(pro*100);
                        float downSize = fileInfo.getDownloadLocation() / 1024.0f / 1024;
                        float totalSize = fileInfo.getSize()  / 1024.0f / 1024;
                        Log.i("LLL","接收到广播获取已下载的进度"+downSize);
                        StringBuilder sb = new StringBuilder();
                        sb.append(appName);
//                        sb.append(" 当前状态: " + DebugUtils.getStatusDesc(fileInfo.getDownloadStatus()) + " \t ");
                        sb.append("\t"+Utils_Parse.getTwoDecimalsStr(downSize) + "M/" + Utils_Parse.getTwoDecimalsStr(totalSize) + "M\t" + "( " + progress + "% )");
                        Log.i(TAG,fileInfo.getDownloadStatus()+"---");


                        for (int i=0;i<mList.size();i++) {
                            if (fileInfo.getFilePath().equals(mList.get(i).getFile().getAbsolutePath())){
                                mList.get(i).setAnInt(progress);
                                if (fileInfo.getDownloadStatus()==DownloadStatus.COMPLETE){
                                    mList.get(i).setType(DownloadStatus.COMPLETE);
                                }
                                mList.get(i).setFileName(sb.toString());
                            }

                        }
                        adapter.updateData(mList);
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void initUI() {
        mList=Text.getData1();
        mDownloadHelper = DownloadHelper.getInstance();
        for (int i=0;i<mList.size();i++){
            downFirstApk(mList.get(i).getUrl(),mList.get(i).getFile());
            pauseFirstApk(mList.get(i).getUrl(),mList.get(i).getFile());
        }


        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapter =   new CommonAdapter<TextClass>(this,R.layout.activity_simple_main, mList) {
            @Override
            public void convert(final ViewHolder holder, final TextClass s) {
//                recyclerView.getAdapter().getItemCount()
//                public static final int WAIT = 42;       //等待
//                public static final int PREPARE = 43;    //准备
//                public static final int LOADING = 44;    //下载中
//                public static final int PAUSE = 45;      //暂停Todo
//                public static final int COMPLETE = 46;   //完成
//                public static final int FAIL = 47;       //失败


                if (s.getType()==DownloadStatus.PREPARE){
                    holder.setText(R.id.btn,START);
                }else if (s.getType()==DownloadStatus.PAUSE){
                    holder.setText(R.id.btn,PAUSE);
                }else if (s.getType()==DownloadStatus.COMPLETE){
                    holder.setText(R.id.btn,"完成");
                }
//                holder.setText(R.id.btn,START);
                holder.setOnClickListener(R.id.btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position=holder.getAdapterPosition();
                        Log.i(TAG,position+"--------------");
//                        switch (s.getType()){
//                            case DownloadStatus.WAIT:
//                                holder.setText(R.id.btn,"等待");
//                                break;
//                            case DownloadStatus.PREPARE:
//                                holder.setText(R.id.btn,"准备");
//                                break;
//                            case DownloadStatus.LOADING:
//                                holder.setText(R.id.btn,"下载中");
//                                break;
//                            case DownloadStatus.PAUSE:
//                                holder.setText(R.id.btn,"暂停");
//                                break;
//                            case DownloadStatus.COMPLETE:
//                                holder.setText(R.id.btn,"完成");
//                                break;
//                            case DownloadStatus.FAIL:
//                                holder.setText(R.id.btn,"失败");
//                                break;
//                        }
//                        String content = ((TextView)holder.getView(R.id.btn)).getText().toString().trim();
                        if (s.getType()==DownloadStatus.PREPARE){
                            Log.i(TAG, "开始下载");
                            //判断当前下载的状态是不是开始状态，如果不是开始状态就开始下载
                            downFirstApk(s.getUrl(),s.getFile());
                            s.setType(DownloadStatus.PAUSE);
                            holder.setText(R.id.btn,PAUSE);
                        }else if (s.getType()==DownloadStatus.PAUSE){
                            Log.i(TAG, "暂停下载");
                            //如果当前是开始状态就暂停下载
                            pauseFirstApk(s.getUrl(),s.getFile());
                            s.setType(DownloadStatus.PREPARE);
                            holder.setText(R.id.btn,START);
                        }
                    }
                });
                holder.setOnClickListener(R.id.deleteBtn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils_Download.deleteFile(s.getFile());
                        s.setAnInt(0);
                        s.setType(DownloadStatus.PREPARE);
                        notifyDataSetChanged();
                    }
                });
                holder.setText(R.id.title,s.getFileName());
                holder.setProgress(R.id.progressBar,s.getAnInt());
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
//        initView();
//        initListener();

    }

    @Override
    protected void initData() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BC_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_caching;
    }

        //删除按钮的点击事件
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //判断文件是否存在，如果存在就删除，如果不存在就提示用户文件不存在
//
//                if (mFile.exists()){
//                    boolean result = mFile.delete();//判断文件删除是否成功
//                    String resultStr = result ? "成功" : "失败";//给用户提示
//                    Utils_Toast.show(getBaseContext(), "删除 mFile  " + resultStr);
//
//                } else {
//                    Utils_Toast.show(getBaseContext(), "不存在 mFile ");
//                }
//            }
//        });
//    }

    //开始下载
    private void downFirstApk(String url,File mFile){
//        for (int i=0;i<mList.size();i++){
//            pauseFirstApk(mList.get(i).getUrl(),mList.get(i).getFile());
//        }
        DownloadHelper.getInstance().addTask(url, mFile, BC_ACTION).submit(this);
    }
    //暂停下载
    private void pauseFirstApk(String url,File mFile){
        DownloadHelper.getInstance().pauseTask(url, mFile, BC_ACTION).submit(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁的时候，解除广播的接收
        unregisterReceiver(receiver);
    }





}
