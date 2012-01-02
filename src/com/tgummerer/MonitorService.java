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

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MonitorService extends Service {

    private int pid = 0;
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
        //Bundle extras = intent.getExtras();
        //if (extras != null) {
        //    pid = extras.getInt("pid");
        //    type = extras.getInt("type");
        //}

        //t = new Thread(new MonitorThread(pid, type));
        //t.start();
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("test");
            }
        },0, 1, TimeUnit.SECONDS);
        // Run the service until it is explicitly stopped
        return START_STICKY;
    }
    

    @Override
    public void onDestroy()
    {
        System.out.println("Stop thread");
        executor.shutdownNow();
    }
}
