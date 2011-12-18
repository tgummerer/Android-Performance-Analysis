/*
 * Performance analysis
 * 
 * Monitor apps running on the phone
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MonitorActivity extends Activity {

    private ExpandableListAdapter taskMonitorAdapter;

    private ActivityManager activityManager;
    Object[] taskinfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);
        
        activityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        // Don't think there will ever be more then 65535 tasks or services running at once
        taskinfo = activityManager.getRunningAppProcesses().toArray();

        taskMonitorAdapter = new MonitorAdapter();
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.monitorView);
        listView.setAdapter(taskMonitorAdapter); 
	}

    public class MonitorAdapter extends BaseExpandableListAdapter {

    	@Override
		public ActivityManager.RunningAppProcessInfo getChild(int categoryPosition, int childPosition) {
            return (RunningAppProcessInfo)taskinfo[categoryPosition];
        }

    	@Override
    	public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

    	@Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) MonitorActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.monitorchild, null);
	        }
	        TextView textView = (TextView) convertView.findViewById(R.id.appimportance);
	        textView.setText(getImportance(getGroup(groupPosition).importance));
	        
	        int[] pid = {getGroup(groupPosition).pid};
	        MemoryInfo[] info = activityManager.getProcessMemoryInfo(pid);

	        TextView memView = (TextView) convertView.findViewById(R.id.dalvikpss);
	        memView.setText(String.valueOf(info[0].dalvikPss));

            memView = (TextView) convertView.findViewById(R.id.dalvikprivatedirty);
            memView.setText(String.valueOf(info[0].dalvikPrivateDirty));

            memView = (TextView) convertView.findViewById(R.id.dalvikshareddirty);
            memView.setText(String.valueOf(info[0].dalvikSharedDirty));

            memView = (TextView) convertView.findViewById(R.id.nativepss);
            memView.setText(String.valueOf(info[0].nativePss));

            memView = (TextView) convertView.findViewById(R.id.nativeprivatedirty);
            memView.setText(String.valueOf(info[0].nativePrivateDirty));

            memView = (TextView) convertView.findViewById(R.id.nativeshareddirty);
            memView.setText(String.valueOf(info[0].nativeSharedDirty));

            memView = (TextView) convertView.findViewById(R.id.otherpss);
            memView.setText(String.valueOf(info[0].otherPss));

            memView = (TextView) convertView.findViewById(R.id.otherprivatedirty);
            memView.setText(String.valueOf(info[0].otherPrivateDirty));

            memView = (TextView) convertView.findViewById(R.id.othershareddirty);
            memView.setText(String.valueOf(info[0].otherSharedDirty));

            return convertView;
        }

        // Format importance in "human readable" format.
        public String getImportance(int importance) {
            switch (importance) {
                case RunningAppProcessInfo.IMPORTANCE_BACKGROUND: return "Background";
                case RunningAppProcessInfo.IMPORTANCE_EMPTY: return "Empty";
                case RunningAppProcessInfo.IMPORTANCE_FOREGROUND: return "Foreground";
                case RunningAppProcessInfo.IMPORTANCE_PERCEPTIBLE: return "Perceptible";
                case RunningAppProcessInfo.IMPORTANCE_SERVICE: return "Service";
                case RunningAppProcessInfo.IMPORTANCE_VISIBLE: return "Visible";
            }
            return "Other";
        }
    	
    	@Override
        public int getChildrenCount(int groupPostion) {
            // One view as child
            return 1;
        }

    	@Override
        public ActivityManager.RunningAppProcessInfo getGroup(int categoryPosition) {
            return (RunningAppProcessInfo)taskinfo[categoryPosition];
        }

    	@Override
        public int getGroupCount() {
            return taskinfo.length;
        }

        @Override
        public long getGroupId(int categoryPosition) {
            return categoryPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        	TextView textView = getGenericView();
			textView.setText(getGroup(groupPosition).processName);
			return textView;
        }
        
        public TextView getGenericView(){
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
			TextView textView = new TextView(MonitorActivity.this);
			textView.setLayoutParams(lp);
			textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			textView.setPadding(50, 0, 0, 0);
			return textView;
		}

        
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
