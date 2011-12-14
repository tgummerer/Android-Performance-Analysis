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
import android.app.ActivityManager.RunningTaskInfo;
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
    private ExpandableListAdapter serviceMonitorAdapter;

    private ActivityManager activityManager;
    Object[] taskinfo;
    Object[] serviceinfo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);
        
        activityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
        // Don't think there will ever be more then 65535 tasks or services running at once
        taskinfo = activityManager.getRunningTasks(65535).toArray();
        serviceinfo = activityManager.getRunningServices(65535).toArray();

        taskMonitorAdapter = new TaskMonitorAdapter();
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.taskMonitorView);
        listView.setAdapter(taskMonitorAdapter); 
        
        serviceMonitorAdapter = new ServiceMonitorAdapter();
        ExpandableListView serviceListView = (ExpandableListView)findViewById(R.id.serviceMonitorView);
        serviceListView.setAdapter(serviceMonitorAdapter); 
	}

    public class TaskMonitorAdapter extends BaseExpandableListAdapter {

    	@Override
		public ActivityManager.RunningTaskInfo getChild(int categoryPosition, int childPosition) {
            return (RunningTaskInfo)taskinfo[categoryPosition];
        }

    	@Override
    	public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

    	@Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) MonitorActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.monitortaskchild, null);
	        }
			TextView textView = (TextView) convertView.findViewById(R.id.tasknumactivities);
	        textView.setText(String.valueOf(getChild(groupPosition, childPosition).numActivities));
	        
	        TextView textView2 = (TextView) convertView.findViewById(R.id.tasknumrunning);
	        textView2.setText(String.valueOf(getGroup(groupPosition).numRunning));
	        
	        TextView memView = (TextView) convertView.findViewById(R.id.taskmemoryusage);
	        int[] pid = {getGroup(groupPosition).id};
	        MemoryInfo[] info = activityManager.getProcessMemoryInfo(pid);
	        memView.setText(String.valueOf(info[0].otherPss));
            return convertView;
        }
    	
    	@Override
        public int getChildrenCount(int groupPostion) {
            // One view as child
            return 1;
        }

    	@Override
        public ActivityManager.RunningTaskInfo getGroup(int categoryPosition) {
            return (RunningTaskInfo)taskinfo[categoryPosition];
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
        	ComponentName cn = getGroup(groupPosition).baseActivity;
			textView.setText(cn.flattenToShortString());
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
    
    public class ServiceMonitorAdapter extends BaseExpandableListAdapter {

    	@Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

    	@Override
    	public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

    	@Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }
    	
    	@Override
        public int getChildrenCount(int groupPostion) {
            return 0;
        }

    	@Override
        public ActivityManager.RunningServiceInfo getGroup(int categoryPosition) {
            return (RunningServiceInfo)serviceinfo[categoryPosition];
        }

    	@Override
        public int getGroupCount() {
            return serviceinfo.length;
        }

        @Override
        public long getGroupId(int categoryPosition) {
            return categoryPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        	TextView textView = getGenericView();
        	ComponentName cn = getGroup(groupPosition).service;
			textView.setText(cn.flattenToShortString());
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
