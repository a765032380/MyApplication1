package com.example.gll.myapplication.util.download;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yaoxiaowen.download.FileInfo;
import com.yaoxiaowen.download.utils.DebugUtils;

import java.io.File;

public class Utils_Download {
    //获取下载路径，如果存在，就返回文件对象，如果不存在，就创建相应的文件夹
    public static File getDir(Context context){
        File dir = new File(context.getExternalCacheDir(), "download");
        if (!dir.exists()){
            dir.mkdirs();
        }
        return dir;
    }
    public static void updateTextview(TextView textView, ProgressBar progressBar, FileInfo fileInfo, String fileName){
        float pro = (float) (fileInfo.getDownloadLocation()*1.0/ fileInfo.getSize());
        int progress = (int)(pro*100);
        float downSize = fileInfo.getDownloadLocation() / 1024.0f / 1024;
        float totalSize = fileInfo.getSize()  / 1024.0f / 1024;

        StringBuilder sb = new StringBuilder();
        sb.append(fileName);
        sb.append(" 当前状态: " + DebugUtils.getStatusDesc(fileInfo.getDownloadStatus()) + " \t ");
        sb.append(Utils_Parse.getTwoDecimalsStr(downSize) + "M/" + Utils_Parse.getTwoDecimalsStr(totalSize) + "M\n" + "( " + progress + "% )");
        textView.setText(sb.toString());
        progressBar.setProgress(progress);
    }
    public static void deleteFile(File mFile){
        if (mFile!=null && mFile.exists()){
            mFile.delete();
        }
    }
}
