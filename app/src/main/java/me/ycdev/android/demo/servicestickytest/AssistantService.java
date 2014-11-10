package me.ycdev.android.demo.servicestickytest;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.os.Process;

import me.ycdev.android.demo.servicestickytest.utils.AppLogger;

public class AssistantService extends Service {
    private static final String TAG = "AssistantService";

    private static final String ACTION_RUN_TEST = "me.ycdev.android.demo.servicestickytest.ACTION_RUN_TEST";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.i(TAG, "onStartCommand: " + intent);
        String action = null;
        if (intent != null) {
            action = intent.getAction();
        }
        if (ACTION_RUN_TEST.equals(action)) {
            new Thread() {
                @Override
                public void run() {
                    SystemClock.sleep(15 * 1000); // 15 seconds
                    stopSelf();
                    Process.killProcess(Process.myPid());
                }
            }.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
