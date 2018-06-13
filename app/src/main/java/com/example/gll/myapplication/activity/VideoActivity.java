package com.example.gll.myapplication.activity;


import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bumptech.glide.Glide;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;
import com.example.gll.myapplication.bean.SwitchVideoModel;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by gll on 2018/5/14.
 */

public class VideoActivity extends GSYBaseActivityDetail<ListGSYVideoPlayer> {
//    private NiceVideoPlayer mNiceVideoPlayer;


    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;
    @BindView(R.id.ad_player)
    GSYSampleADVideoPlayer detailPlayer;
    @BindView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initVideoPlayer();

    }

    private void initVideoPlayer(){
        //普通模式
        initVideo();

        ArrayList<GSYSampleADVideoPlayer.GSYADVideoModel> urls = new ArrayList<>();
        //广告1
        urls.add(new GSYSampleADVideoPlayer.GSYADVideoModel("http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4",
                "正文标题1", GSYSampleADVideoPlayer.GSYADVideoModel.TYPE_AD,false));
        //正式内容1
//        urls.add(new GSYSampleADVideoPlayer.GSYADVideoModel("http:/*//9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4",
//                "正文标题2", GSYSampleADVideoPlayer.GSYADVideoModel.TYPE_NORMAL));
//        //广告2
//        urls.add(new GSYSampleADVideoPlayer.GSYADVideoModel("http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4",
//                "正文标题2", GSYSampleADVideoPlayer.GSYADVideoModel.TYPE_NORMAL));
        //正式内容2
//        urls.add(new GSYSampleADVideoPlayer.GSYADVideoModel("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4",
//                "正文2标题", GSYSampleADVideoPlayer.GSYADVideoModel.TYPE_NORMAL));
        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);

        String source2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
        String name2 = "清晰";
        SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);
        list.add(switchVideoModel2);

        detailPlayer.setAdUp(urls, true, 0);
//        detailPlayer.setUp(list,true,"测试视频" );
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        detailPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        detailPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);

        detailPlayer.setVideoAllCallBack(this);

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });

    }
    @Override
    public ListGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //不需要builder的
        return null;
    }

    @Override
    public void clickForFullScreen() {

    }

    /**
     * 是否启动旋转横屏，true表示启动
     *
     * @return true
     */
    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void onEnterFullscreen(String url, Object... objects) {
        super.onEnterFullscreen(url, objects);
        //隐藏调全屏对象的返回按键
        GSYVideoPlayer gsyVideoPlayer = (GSYVideoPlayer) objects[1];
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
    }


    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
    }





//    private void initNiceVideoPlayer(){
//        mNiceVideoPlayer = (NiceVideoPlayer) findViewById(R.id.nice_video_player);
//        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // or NiceVideoPlayer.TYPE_NATIVE
//        mNiceVideoPlayer.setUp("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4", null);
//
//        TxVideoPlayerController controller = new TxVideoPlayerController(this);
//        controller.setTitle("ssss");
//        ImageLoaderManager.getInstance(this)
//                .displayImage(controller.imageView(),"http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg");
//        controller.imageView().setBackgroundResource(R.drawable.ic_launcher_background);
////        controller.setImage(R.drawable.ic_launcher_background);
//        mNiceVideoPlayer.setController(controller);
//
////        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(RecycleActivity.class);
////            }
////        });
////        findViewById(R.id.mButton1).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(ImageActivity.class);
////            }
////        });
//    }


}
