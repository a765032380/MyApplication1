package com.example.gll.myapplication.util;

import android.view.View;

/*
 * Copyright (C) 2010-2017 Alibaba Group Holding Limited.
 */
public abstract class OnClickListener implements View.OnClickListener {

    private long lastClickTime = 0;
    private final int SPACE_TIME = 500;


    public boolean isDoubleClick() {
        long currentTime = System.currentTimeMillis();
        boolean isDoubleClick;
        if (currentTime - lastClickTime > SPACE_TIME) {
            isDoubleClick = false;
        } else {
            isDoubleClick = true;
        }
        lastClickTime = currentTime;
        return isDoubleClick;
    }
}
