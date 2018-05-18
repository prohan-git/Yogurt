package com.example.prohan.yogurt;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

public class YogurtApp extends Application {
    public static YogurtApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();

    }

    private void init() {
        //初始化Leak内存泄露检测工具
        LeakCanary.install(this);
        //初始化Bugly
//        Bugly.init(getApplicationContext(), "8c3e26b57c", false);
    }

    public static YogurtApp getInstance() {
        return mInstance;
    }

}
