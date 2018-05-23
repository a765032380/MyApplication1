package com.example.gll.myapplication.util;

import com.example.gll.myapplication.app.MyApp;
import com.example.gll.myapplication.bean.TextClass;
import com.example.gll.myapplication.util.download.Utils_Download;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Text
{
    private static String URL="http://imtt.dd.qq.com/16891/9A7CBD9CAFF7AA35E754408E2D2C6288.apk?fsname=com.tencent.mm_6.6.6_1300.apk&csr=1bbd";
    public static ArrayList<String> getData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add("测试数据"+i);
        }
        return mList;
    }
    public static ArrayList<TextClass> getData1(){
        ArrayList<TextClass> mList=new ArrayList<>();
            TextClass textClass1=new TextClass();
            textClass1.setString("测试数据"+1);
            textClass1.setUrl(URL);
            textClass1.setFile(new File(Utils_Download.getDir(MyApp.app), 1+"测试数据.apk"));

        TextClass textClass2=new TextClass();
        textClass2.setString("测试数据"+2);
        textClass2.setUrl(URL);
        textClass2.setFile(new File(Utils_Download.getDir(MyApp.app), 2+"测试数据.apk"));

        TextClass textClass3=new TextClass();
        textClass3.setString("测试数据"+3);
        textClass3.setUrl(URL);
        textClass3.setFile(new File(Utils_Download.getDir(MyApp.app), 3+"测试数据.apk"));

        TextClass textClass4=new TextClass();
        textClass4.setString("测试数据"+4);
        textClass4.setUrl(URL);
        textClass4.setFile(new File(Utils_Download.getDir(MyApp.app), 4+"测试数据.apk"));

        TextClass textClass5=new TextClass();
        textClass5.setString("测试数据"+5);
        textClass5.setUrl(URL);
        textClass5.setFile(new File(Utils_Download.getDir(MyApp.app), 5+"测试数据.apk"));

        mList.add(textClass1);
        mList.add(textClass2);
        mList.add(textClass3);
        mList.add(textClass4);
        mList.add(textClass5);
        return mList;
    }
}
