/*
 * Performance analysis
 * 
 * Service for monitoring apps.
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.os.IBinder;

public class MonitorService extends Service {

    private int[] pid;
    private int type = 0;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        super.onStartCommand(intent, flags, startId);
        Bundle extras = intent.getExtras();
        DatabaseHelper dbHelper = new DatabaseHelper(MonitorService.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("monitor", null, null);
        db.close();
        if (extras != null) {
            int[] pid = {extras.getInt("pid")};
            this.pid = pid;
            type = extras.getInt("type");
        }

        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                int memusage;

                DatabaseHelper dbHelper = new DatabaseHelper(MonitorService.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                MemoryInfo[] info = activityManager.getProcessMemoryInfo(pid);
                if (type == 1)
                    memusage = info[0].getTotalPss();
                else if (type == 2)
                    memusage = info[0].getTotalPrivateDirty();
                else
                    memusage = info[0].getTotalSharedDirty();
                
                ContentValues values = new ContentValues(2);
                values.put("pid", pid[0]);
                values.put("type", type);
                values.put("memusage", memusage);
                db.insert("monitor", null, values);
                db.close();
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Run the service until it is explicitly stopped
        return START_STICKY;
    }
    

    @Override
    public void onDestroy()
    {
        // Stop the ScheduledExecutorService
        executor.shutdownNow();
    }
}
