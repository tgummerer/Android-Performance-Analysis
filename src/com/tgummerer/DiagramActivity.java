/*
 * Performance analysis
 * 
 * Class instanciating the DiagramView, which itself calls the C++ code for the OpenGL graphics
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Activity;
import android.os.Bundle;

public class DiagramActivity extends Activity {

    private DiagramView view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        view = new DiagramView(getApplication());
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
