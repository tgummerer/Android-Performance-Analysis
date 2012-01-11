/*
 * Performance analysis
 * 
 * Select what tests should be executed
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SelectTestsActivity extends Activity {
    
    private String PREFS_NAME = "ALGORITHMS";
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecttests);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        CheckBox cb = (CheckBox) findViewById(R.id.algorithm1);
        cb.setChecked(settings.getBoolean("algorithm1", true));

        cb = (CheckBox) findViewById(R.id.algorithm2);
        cb.setChecked(settings.getBoolean("algorithm2", true));

        cb = (CheckBox) findViewById(R.id.algorithm3);
        cb.setChecked(settings.getBoolean("algorithm3", true));

        cb = (CheckBox) findViewById(R.id.algorithm4);
        cb.setChecked(settings.getBoolean("algorithm4", true));

        cb = (CheckBox) findViewById(R.id.memtest);
        cb.setChecked(settings.getBoolean("memtest", true));
    }

    public void save(View v) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        
        CheckBox cb = (CheckBox) findViewById(R.id.algorithm1);
        editor.putBoolean("algorithm1", cb.isChecked());

        cb = (CheckBox) findViewById(R.id.algorithm2);
        editor.putBoolean("algorithm2", cb.isChecked());

        cb = (CheckBox) findViewById(R.id.algorithm3);
        editor.putBoolean("algorithm3", cb.isChecked());

        cb = (CheckBox) findViewById(R.id.algorithm4);
        editor.putBoolean("algorithm4", cb.isChecked());

        cb = (CheckBox) findViewById(R.id.memtest);
        cb.setChecked(settings.getBoolean("memtest", true));

        editor.commit();
        setResult(RESULT_OK);
        finish();
    }

    public void cancel(View v) {
        // Do nothing, just close the activity
        setResult(RESULT_CANCELED);
        finish();
    }
}
