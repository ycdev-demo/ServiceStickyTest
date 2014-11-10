package me.ycdev.android.demo.servicestickytest;

import android.app.Application;
import android.os.SystemClock;

import me.ycdev.android.demo.servicestickytest.utils.AppLogger;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        AppLogger.i(TAG, "#onCreate");

        new Thread() {
            @Override
            public void run() {
                SystemClock.sleep(1000); // 1 seconds
                TestService.onAppStart(getApplicationContext());
            }
        }.start();
    }
}
