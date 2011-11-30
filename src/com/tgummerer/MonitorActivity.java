/*
 * Performance analysis
 * 
 * Monitor apps running on the phone
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class MonitorActivity extends Activity {

    ExpandableListAdapter monitorAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor);

        monitorAdapter = new MonitorAdapter();
        ExpandableListView listView = (ExpandableListView)findViewById(R.id.monitorView);
        listView.setAdapter(monitorAdapter);
	}

    public class MonitorAdapter extends BaseExpandableListAdapter {
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
            return null;
        }

    	@Override
        public int getGroupCount() {
            return 0;
        }

        @Override
        public long getGroupId(int categoryPosition) {
            return categoryPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            return null;
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
