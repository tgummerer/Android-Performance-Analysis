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
import android.os.Bundle;
import android.view.View;

public class SelectTestsActivity extends Activity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecttests);
    }
}
