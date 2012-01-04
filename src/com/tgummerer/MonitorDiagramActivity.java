/*
 * Performance analysis
 * 
 * Class instanciating the DiagramView, which itself calls the C++ code for the OpenGL graphics, displaying the monitored memory usage
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Activity;
import android.os.Bundle;

public class MonitorDiagramActivity extends Activity {

    private MonitorDiagramView view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        view = new MonitorDiagramView(getApplication());
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }
}
