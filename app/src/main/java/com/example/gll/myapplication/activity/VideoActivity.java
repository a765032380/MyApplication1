package com.example.gll.myapplication.activity;


import android.view.View;
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
        mNiceVideoPlayer.setUp("http://mp4.vjshi.com/2016-04-05/add12db77c7c5cd6dfef4c1955b36a80.mp4", null);

        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("ssss");
//        controller.setImage(mImageUrl);
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
