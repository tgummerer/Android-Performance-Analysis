/*
 * Performance analysis
 * 
 * Test activity, executes all tests
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import java.util.Random;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Progress extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        
        Algorithms alg = new Algorithms();
        alg.execute((Void[])null);
        
        CAlgorithms calg = new CAlgorithms();
        calg.execute((Void[])null);
    }
    
    public void algorithmStarted(long algorithmID) {
    	TextView tv = (TextView)findViewById(R.id.progress_textview);
    	tv.append("Algorithm " + algorithmID + " started...\n");
    }
    
    public void algorithmEnded(long algorithmID, long time) {
    	TextView tv = (TextView)findViewById(R.id.progress_textview);
    	tv.append("Algorithm " + algorithmID + " completed in " + time + "\n");
    }
    
    private class Algorithms extends AsyncTask<Void, Long, Void> {
    	
    	public Algorithms() {

    	}
    	
    	@Override
    	protected Void doInBackground(Void... params) {
    		forloop();
    		sort();
    		return null;
    	}
    	
    	protected void onProgressUpdate(Long... progress) {
    		if (progress[1] == 0) {
    			algorithmStarted(progress[0]);
    		} else {
    			algorithmEnded(progress[0], progress[1]);
    		}
    	}
    	
    	private void showProgress(long algorithmID, long time) {
    		Long[] array = new Long[2];
    		array[0] = algorithmID;
    		array[1] = time;
    		publishProgress(array);
    	}
    	
    	// Algorithm 1, for loop doing nothing 
    	private void forloop () {
    		showProgress(1, 0);
    		// Using System.nanoTime instead of System.getTimeInMillis is more accurate
    		// according to http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
    		long startTime = System.nanoTime();
    		
    		// ALGORITHM
    		for (int i = 0; i < 100000; i++);
    		// END ALGORITHM
    		
    		long endTime = System.nanoTime();
    		showProgress(1, endTime - startTime);
    	}

        // Algorithm 2, Random quicksort of a backwards sorted array of size 10000
    	// If it wouldn't be random there is a high possibility of a stackoverflow
        private void sort() {
        	showProgress(2, 0);
    		long startTime = System.nanoTime();
    		// ALGORITHM
    		int[] arr = new int[100000];
            for (int i = 0; i < arr.length; i++)
                arr[i] = arr.length - i;
            
            quicksort(arr, 0, arr.length - 1);
            // END ALGORITHM
            
            long endTime = System.nanoTime();
    		showProgress(2, endTime - startTime);
        }

        private void quicksort(int arr[], int start, int end) {
            int i = start;
            int k = end;
            Random r = new Random();

            if (end - start >= 1) {
                int p = r.nextInt(end - start);
                p = p + start;
                int pivot = arr[p];
                while (k > i) {
                    while (arr[i] <= pivot && i <= end && k > i)
                        i++;
                    while (arr[k] > pivot && k >= start && k >= i)
                        k--;
                    if (k > i)
                        swap(arr, i, k);
                }
                swap(arr, start, k);

                quicksort(arr, start, k - 1);
                quicksort(arr, k + 1, end);
            } else 
                return;
        }

        private void swap(int arr[], int i, int k) {
            int t = arr[i];
            arr[i] = arr[k];
            arr[k] = t;
        }	
    }
}	
