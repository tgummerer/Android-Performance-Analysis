package com.tgummerer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private final String createString = "create table measurements " +
			"(id integer primary key autoincrement, " +
			"langid integer, " +
			"algorithmid integer, " +
			"time long);";
	private final static int dbversion = 1;
	private final static String dbname = "PerformanceAnalysis";
	public DatabaseHelper(Context context) {
		super(context, dbname, null, dbversion);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createString);
		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists measurements");
		onCreate(db);
		db.close();
	}

}
