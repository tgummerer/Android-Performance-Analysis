/*
 * Performance analysis
 * 
 * Export data to SD Card
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

public class ExportActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean success = exportData();
        setContentView(R.layout.export);

        TextView tv = (TextView) findViewById(R.id.export);
        if (success)
            tv.setText("Data written successfully to performance.csv");
        else
            tv.setText("Data could not be written. (Missing SD Card?)");
    }

    public boolean exportData() {
        String filename = "performance.csv";
        File file = new File(Environment.getExternalStorageDirectory(), filename);
        FileOutputStream fos;
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        try {
            fos = new FileOutputStream(file);
            byte[] data = new String("Time consumption in ns. LanguageID (0 = Java, 1 = C), AlgorithmID, Time\n").getBytes();
            fos.write(data);
            Cursor c = db.rawQuery("select * from measurements order by algorithmid asc, langid asc", null); 
            while (c.moveToNext()) {
                data = new String(c.getInt(1) + ", " + c.getInt(2) + ", " + c.getInt(3) + "\n").getBytes();
                fos.write(data);
            }

            data = new String("Memory consumption in kb. LanguageID (0 = Java, 1 = C), MemoryConsumption\n").getBytes();
            fos.write(data);
            c = db.rawQuery("select * from memtests order by type", null);
            while (c.moveToNext()) {
                data = new String(c.getInt(1) + ", " + c.getInt(2) + "\n").getBytes();
                fos.write(data);
            }
            fos.flush();
            fos.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
