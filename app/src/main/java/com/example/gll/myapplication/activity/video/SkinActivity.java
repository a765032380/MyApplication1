package com.example.gll.myapplication.activity.video;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.aliyun.vodplayer.media.IAliyunVodPlayer;
import com.aliyun.vodplayerview.utils.ScreenUtils;
import com.aliyun.vodplayerview.widget.AliyunVodPlayerView;
import com.example.gll.myapplication.R;
import com.example.gll.myapplication.util.OnClickListener;
import com.example.gll.myapplication.util.ScreenStatusController;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SkinActivity extends AppCompatActivity {

    private AliyunVodPlayerView mAliyunVodPlayerView = null;

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS");
    private List<String> logStrs = new ArrayList<>();

    private ScreenStatusController mScreenStatusController = null;

    private boolean isStrangePhone() {
        boolean strangePhone = Build.DEVICE.equalsIgnoreCase("mx5")
                || Build.DEVICE.equalsIgnoreCase("Redmi Note2")
                || Build.DEVICE.equalsIgnoreCase("Z00A_1")
                || Build.DEVICE.equalsIgnoreCase("hwH60-L02")
                || Build.DEVICE.equalsIgnoreCase("hermes")
                || (Build.DEVICE.equalsIgnoreCase("V4") && Build.MANUFACTURER.equalsIgnoreCase("Meitu"))
                || (Build.DEVICE.equalsIgnoreCase("m1metal") && Build.MANUFACTURER.equalsIgnoreCase("Meizu"));

//        VcPlayerLog.e("lfj1115 ", " Build.Device = " + Build.DEVICE + " , isStrange = " + strangePhone);
        return strangePhone;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (isStrangePhone()) {
//            setTheme(R.style.ActTheme);
        } else {
            setTheme(R.style.NoActionTheme);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_skin);

//        findViewById(R.id.log).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isDoubleClick()) {
//                    return;
//                }
//                LayoutInflater inflater = LayoutInflater.from(SkinActivity.this);
//                View view = inflater.inflate(R.layout.view_log, null);
//
//                TextView textview = (TextView) view.findViewById(R.id.log);
//                if (mAliyunVodPlayerView != null) {
//                    for (String log : logStrs) {
//                        textview.append("     " + log + "\n");
//                    }
//                }
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SkinActivity.this);
//                alertDialog.setTitle(R.string.player_log);
//                alertDialog.setView(view);
//                alertDialog.setPositiveButton(R.string.ok, null);
//                AlertDialog alert = alertDialog.create();
//                alert.show();
//            }
//        });
        findViewById(R.id.blue).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Blue);
            }
        });
        findViewById(R.id.green).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Green);
            }
        });
        findViewById(R.id.orange).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Orange);
            }
        });
        findViewById(R.id.red).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Red);
            }
        });

        findViewById(R.id.change_source).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDoubleClick()) {
                    return;

                }
                setPlaySource();
            }
        });

        mAliyunVodPlayerView = (AliyunVodPlayerView) findViewById(R.id.video_view);
        mAliyunVodPlayerView.setKeepScreenOn(true);//保持屏幕敞亮

        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save_cache";
        mAliyunVodPlayerView.setPlayingCache(true, sdDir, 60 * 60 /*时长, s */, 300 /*大小，MB*/);
        mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Orange);
        mAliyunVodPlayerView.setCirclePlay(true);
        mAliyunVodPlayerView.setAutoPlay(true);
        //设置播放器封面，封面地址和标题可以从服务端下发，封面地址请使用https 地址。
//        mAliyunVodPlayerView.setCoverUri(uri)
//        mAliyunVodPlayerView.setCoverResource(resId)

        mAliyunVodPlayerView.setOnPreparedListener(new MyPrepareListener(this));
        mAliyunVodPlayerView.setOnCompletionListener(new MyCompletionListener(this));
        mAliyunVodPlayerView.setOnFirstFrameStartListener(new MyFrameInfoListener(this));
        mAliyunVodPlayerView.setOnChangeQualityListener(new MyChangeQualityListener(this));
        mAliyunVodPlayerView.setOnStoppedListener(new MyStoppedListener(this));
        mAliyunVodPlayerView.enableNativeLog();

        setPlaySource();

        mScreenStatusController = new ScreenStatusController(this);
        mScreenStatusController.setScreenStatusListener(new ScreenStatusController.ScreenStatusListener() {
            @Override
            public void onScreenOn() {
            }

            @Override
            public void onScreenOff() {

            }
        });
        mScreenStatusController.startListen();
    }

    private static class MyPrepareListener implements IAliyunVodPlayer.OnPreparedListener {

        private WeakReference<SkinActivity> activityWeakReference;

        public MyPrepareListener(SkinActivity skinActivity) {
            activityWeakReference = new WeakReference<SkinActivity>(skinActivity);
        }

        @Override
        public void onPrepared() {
            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onPrepared();
            }
        }
    }

    private void onPrepared() {
        logStrs.add(format.format(new Date()) + getString(R.string.log_prepare_success));
        Toast.makeText(SkinActivity.this.getApplicationContext(), R.string.toast_prepare_success, Toast.LENGTH_SHORT).show();
    }

    private static class MyCompletionListener implements IAliyunVodPlayer.OnCompletionListener {

        private WeakReference<SkinActivity> activityWeakReference;

        public MyCompletionListener(SkinActivity skinActivity) {
            activityWeakReference = new WeakReference<SkinActivity>(skinActivity);
        }


        @Override
        public void onCompletion() {

            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onCompletion();
            }
        }
    }

    private void onCompletion() {
        logStrs.add(format.format(new Date()) + getString(R.string.log_play_completion));
        Toast.makeText(SkinActivity.this.getApplicationContext(), R.string.toast_play_compleion, Toast.LENGTH_SHORT).show();

    }

    private static class MyFrameInfoListener implements IAliyunVodPlayer.OnFirstFrameStartListener {

        private WeakReference<SkinActivity> activityWeakReference;

        public MyFrameInfoListener(SkinActivity skinActivity) {
            activityWeakReference = new WeakReference<SkinActivity>(skinActivity);
        }

        @Override
        public void onFirstFrameStart() {

            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onFirstFrameStart();
            }
        }
    }

    private void onFirstFrameStart() {
        Map<String, String> debugInfo = mAliyunVodPlayerView.getAllDebugInfo();
        long createPts = 0;
        if (debugInfo.get("create_player") != null) {
            String time = debugInfo.get("create_player");
            createPts = (long) Double.parseDouble(time);
            logStrs.add(format.format(new Date(createPts)) + getString(R.string.log_player_create_success));
        }
        if (debugInfo.get("open-url") != null) {
            String time = debugInfo.get("open-url");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_open_url_success));
        }
        if (debugInfo.get("find-stream") != null) {
            String time = debugInfo.get("find-stream");
            long findPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(findPts)) + getString(R.string.log_request_stream_success));
        }
        if (debugInfo.get("open-stream") != null) {
            String time = debugInfo.get("open-stream");
            long openPts = (long) Double.parseDouble(time) + createPts;
            logStrs.add(format.format(new Date(openPts)) + getString(R.string.log_start_open_stream));
        }
        logStrs.add(format.format(new Date()) + getString(R.string.log_first_frame_played));
    }

    private static class MyChangeQualityListener implements IAliyunVodPlayer.OnChangeQualityListener {

        private WeakReference<SkinActivity> activityWeakReference;

        public MyChangeQualityListener(SkinActivity skinActivity) {
            activityWeakReference = new WeakReference<SkinActivity>(skinActivity);
        }

        @Override
        public void onChangeQualitySuccess(String finalQuality) {

            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualitySuccess(finalQuality);
            }
        }

        @Override
        public void onChangeQualityFail(int code, String msg) {
            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onChangeQualityFail(code, msg);
            }
        }
    }

    private void onChangeQualitySuccess(String finalQuality) {
        logStrs.add(format.format(new Date()) + getString(R.string.log_change_quality_success));
        Toast.makeText(SkinActivity.this.getApplicationContext(), getString(R.string.log_change_quality_success), Toast.LENGTH_SHORT).show();
    }

    void onChangeQualityFail(int code, String msg) {
        logStrs.add(format.format(new Date()) + getString(R.string.log_change_quality_fail) + " : " + msg);
        Toast.makeText(SkinActivity.this.getApplicationContext(), getString(R.string.log_change_quality_fail), Toast.LENGTH_SHORT).show();
    }

    private static class MyStoppedListener implements IAliyunVodPlayer.OnStoppedListener {

        private WeakReference<SkinActivity> activityWeakReference;

        public MyStoppedListener(SkinActivity skinActivity) {
            activityWeakReference = new WeakReference<SkinActivity>(skinActivity);
        }

        @Override
        public void onStopped() {

            SkinActivity activity = activityWeakReference.get();
            if (activity != null) {
                activity.onStopped();
            }
        }
    }

    private void onStopped() {
        Toast.makeText(SkinActivity.this.getApplicationContext(), R.string.log_play_stopped, Toast.LENGTH_SHORT).show();
    }

    private void setPlaySource() {


//        String type = getIntent().getStringExtra("type");
//        if (TextUtils.isEmpty(type)) {
//            return;
//        }
//
//        if (type.equals("localSource")) {

            AliyunLocalSource.AliyunLocalSourceBuilder alsb = new AliyunLocalSource.AliyunLocalSourceBuilder();
            alsb.setSource("http://vod-download.cn-shanghai.aliyuncs.com/testvideo/stsPlayDemo.mp4");
            alsb.setTitle("测试视频");
            AliyunLocalSource localSource = alsb.build();
            mAliyunVodPlayerView.setLocalSource(localSource);

//        } else if (type.equals("vidsts")) {
//
//            String vid = getIntent().getStringExtra("vid");
//            String akId = getIntent().getStringExtra("akId");
//            String akSecret = getIntent().getStringExtra("akSecret");
//            String scuToken = getIntent().getStringExtra("scuToken");
//
//            AliyunVidSts vidSts = new AliyunVidSts();
//            vidSts.setVid(vid);
//            vidSts.setAcId(akId);
//            vidSts.setAkSceret(akSecret);
//            vidSts.setSecurityToken(scuToken);
//            mAliyunVodPlayerView.setVidSts(vidSts);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updatePlayerViewMode();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.onResume();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.onStop();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("lfj1019", " orientation = " + getResources().getConfiguration().orientation);
        updatePlayerViewMode();
    }

    private void updatePlayerViewMode() {
        if (mAliyunVodPlayerView != null) {
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {                //转为竖屏了。
                //显示状态栏
//                if (!isStrangePhone()) {
//                    getSupportActionBar().show();
//                }

                this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                mAliyunVodPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                //设置view的布局，宽高之类
                LinearLayout.LayoutParams aliVcVideoViewLayoutParams = (LinearLayout.LayoutParams) mAliyunVodPlayerView.getLayoutParams();
                aliVcVideoViewLayoutParams.height = (int) (ScreenUtils.getWidth(this) * 9.0f / 16);
                aliVcVideoViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                if (!isStrangePhone()) {
//                    aliVcVideoViewLayoutParams.topMargin = getSupportActionBar().getHeight();
//                }

            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {                //转到横屏了。
                //隐藏状态栏
                if (!isStrangePhone()) {
                    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    mAliyunVodPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }

                //设置view的布局，宽高
                LinearLayout.LayoutParams aliVcVideoViewLayoutParams = (LinearLayout.LayoutParams) mAliyunVodPlayerView.getLayoutParams();
                aliVcVideoViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                aliVcVideoViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                if (!isStrangePhone()) {
//                    aliVcVideoViewLayoutParams.topMargin = 0;
//                }

            }

        }
    }

    @Override
    protected void onDestroy() {
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.onDestroy();
            mAliyunVodPlayerView = null;
        }
        mScreenStatusController.stopListen();

        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAliyunVodPlayerView != null) {
            boolean handler = mAliyunVodPlayerView.onKeyDown(keyCode, event);
            if (!handler) {
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //解决某些手机上锁屏之后会出现标题栏的问题。
//        VcPlayerLog.d("lfj1030", "onWindowFocusChanged = " + hasFocus);
        updatePlayerViewMode();
    }


}
