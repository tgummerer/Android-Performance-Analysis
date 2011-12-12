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
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Gravity;
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
        public Object getGroup(int categoryPosition) {
            return taskinfo[categoryPosition];
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
        	ComponentName cn = ((ActivityManager.RunningTaskInfo)getGroup(groupPosition)).baseActivity;
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
        public Object getGroup(int categoryPosition) {
            return serviceinfo[categoryPosition];
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
        	ComponentName cn = ((ActivityManager.RunningServiceInfo)getGroup(groupPosition)).service;
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
