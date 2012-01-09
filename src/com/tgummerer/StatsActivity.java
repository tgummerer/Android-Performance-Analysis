/*
 * Performance analysis
 * 
 * Show statistics about the tests
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import java.text.DecimalFormat;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor c = db.rawQuery("select avg(m2.time) / avg(m1.time) from measurements m1, measurements m2 where m1.langid = 0 and m2.langid = 1;", null);
        
        DecimalFormat df = new DecimalFormat("###.##%");
        if (c.moveToFirst()) {
        	TextView t = (TextView)findViewById(R.id.avg_difference);
            t.setText(df.format(c.getDouble(0)));
        }

        c = db.rawQuery("select m2.time, m1.time from measurements m1, measurements m2 where m1.langid = 0 and m2.langid = 1 and m1.algorithmid = m2.algorithmid;", null);
        double max = 0;
        while (c.moveToNext()) {
        	double diff = c.getDouble(0) / c.getDouble(1);
        	if (max < diff) 
        		max = diff;
        }
        TextView t = (TextView)findViewById(R.id.max_difference);
        t.setText(df.format(max));
        
        c = db.rawQuery("select m2.time, m1.time from measurements m1, measurements m2 where m1.langid = 0 and m2.langid = 1 and m1.algorithmid = m2.algorithmid;", null);
        // Initialize to a high value, that will surely not be the minimum difference
        double min = 100;
        while (c.moveToNext()) {
        	double diff = c.getDouble(0) / c.getDouble(1);
        	if (min > diff)
        		min = diff;
        }
        t = (TextView)findViewById(R.id.min_difference);
        t.setText(df.format(min));

        db.close();
    }
}
