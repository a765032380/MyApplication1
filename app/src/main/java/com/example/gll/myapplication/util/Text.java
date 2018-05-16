package com.example.gll.myapplication.util;

import java.util.ArrayList;
import java.util.List;

public class Text
{
    public static ArrayList<String> getData(){
        ArrayList<String> mList=new ArrayList<>();
        for (int i=0;i<10;i++){
            mList.add("测试数据"+i);
        }
        return mList;
    }
}
