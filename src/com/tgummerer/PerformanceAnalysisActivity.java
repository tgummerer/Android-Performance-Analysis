/*
 * Performance analysis
 * 
 * Main Activity, mainly executing other activities when the user chooses to
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PerformanceAnalysisActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void startTests(View v) {
    	Intent tests = new Intent(PerformanceAnalysisActivity.this, TestActivity.class);
    	startActivity(tests);
    }
}