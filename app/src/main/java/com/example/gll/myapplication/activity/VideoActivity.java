package com.example.gll.myapplication.activity;


import android.view.View;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.bumptech.glide.Glide;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.base.BaseActivity;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;


/**
 * Created by gll on 2018/5/14.
 */

public class VideoActivity extends BaseActivity {
    private NiceVideoPlayer mNiceVideoPlayer;
    @Override
    protected void initUI() {
        mNiceVideoPlayer = findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // or NiceVideoPlayer.TYPE_NATIVE
        mNiceVideoPlayer.setUp("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-33-30.mp4", null);

        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("ssss");
        ImageLoaderManager.getInstance(this)
                .displayImage(controller.imageView(),"http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg");
        controller.imageView().setBackgroundResource(R.drawable.ic_launcher_background);
//        controller.setImage(R.drawable.ic_launcher_background);
        mNiceVideoPlayer.setController(controller);

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecycleActivity.class);
            }
        });
        findViewById(R.id.mButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ImageActivity.class);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        // 在onStop时释放掉播放器
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }
    @Override
    public void onBackPressed() {
        // 在全屏或者小窗口时按返回键要先退出全屏或小窗口，
        // 所以在Activity中onBackPress要交给NiceVideoPlayer先处理。
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
    @Override
    protected void initData() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }
}
