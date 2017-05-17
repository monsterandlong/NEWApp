package com.bf.com.myapplication.base;

import android.app.Application;

/**
 * Created by 张志龙 on 2017/5/16.
 */

public class App extends Application {
    public static App appContent;
    @Override
    public void onCreate() {
        super.onCreate();
        appContent=this;
    }
}
