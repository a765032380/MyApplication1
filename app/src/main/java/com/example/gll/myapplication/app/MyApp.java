package com.example.gll.myapplication.app;

import android.app.Application;

public class MyApp extends Application {
    public static MyApp app;

    public MyApp(){
        app = this;
    }
    public static MyApp getContent(){
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();

    }
}
