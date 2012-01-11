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
        // Initialize database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
    }
    
    public void startTests(View v) {
    	Intent tests = new Intent(PerformanceAnalysisActivity.this, Progress.class);
    	startActivity(tests);
    }

    public void showDiagram(View v) {
        Intent diagram = new Intent(PerformanceAnalysisActivity.this, DiagramActivity.class);
        startActivity(diagram);
    }
    
    public void monitorApps(View v) {
    	Intent monitor = new Intent(PerformanceAnalysisActivity.this, MonitorActivity.class);
    	startActivity(monitor);
    }

    public void showMonitorDiagram(View v) {
        Intent monitorDiagram = new Intent(PerformanceAnalysisActivity.this, MonitorDiagramActivity.class);
        startActivity(monitorDiagram);
    }

    public void showStats(View v) {
        Intent stats = new Intent(PerformanceAnalysisActivity.this, StatsActivity.class);
        startActivity(stats);
    }

    public void selectTests(View v) {
        Intent tests = new Intent(PerformanceAnalysisActivity.this, SelectTestsActivity.class);
        startActivity(tests);
    }

    public void memTestDiagram(View v) {
        Intent memTestDiagram = new Intent(PerformanceAnalysisActivity.this, MemTestActivity.class);
        startActivity(memTestDiagram);
    }
}
