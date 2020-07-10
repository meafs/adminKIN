package com.sust.adminkinblood.notification;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class App extends Application {
    public static final String EVENT_ID = "events";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

        @SuppressLint("WrongConstant") NotificationChannel channel = new NotificationChannel(EVENT_ID, "Recent Events", NotificationManager.IMPORTANCE_MAX);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(channel);
    }
}
