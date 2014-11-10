package me.ycdev.android.demo.servicestickytest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import me.ycdev.android.demo.servicestickytest.utils.AppLogger;

public class TestService extends IntentService {
    private static final String TAG = "TestService";

    private static final String ACTION_APP_START = "me.ycdev.android.demo.servicestickytest.ACTION_APP_START";

    public TestService() {
        super("TestService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.i(TAG, "#onStartCommand: " + intent + ", startId: " + startId);
        int result = super.onStartCommand(intent, flags, startId);
        AppLogger.i(TAG, "#onStartcommand, startId: " + startId + ", result: " + result);
        return result;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (ACTION_APP_START.equals(action)) {
            AppLogger.d(TAG, "onAppStart, begin...");
            SystemClock.sleep(30 * 1000); // 30 seconds
            AppLogger.d(TAG, "onAppStart, end.");
        }
    }

    public static void onAppStart(Context cxt) {
        Intent intent = new Intent(cxt, TestService.class);
        intent.setAction(ACTION_APP_START);
        cxt.startService(intent);
    }
}
