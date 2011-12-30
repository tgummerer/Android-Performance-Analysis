/*
 * Performance analysis
 * 
 * Service for monitoring apps.
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MonitorService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();
        System.out.println("bla");
        Log.println(1, "bla", "hallo");
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
        int bla = 0;
        if (extras != null)
            bla = extras.getInt("1");

        //Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        System.out.println("hallo");
        Log.println(1, "bla", String.valueOf(bla));
        return 0;
    }

}
