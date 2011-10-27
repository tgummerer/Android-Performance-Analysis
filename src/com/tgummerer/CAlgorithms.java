package com.tgummerer;

import java.util.Random;

import android.os.AsyncTask;

public class CAlgorithms extends AsyncTask<Void, Long, Void> {
	
	@Override
	protected Void doInBackground(Void... params) {
		forloop();
		sort();
		return null;
	}
	
	protected void onProgressUpdate(Long... progress) {
		System.out.println(progress[0] + " " + progress[1]);
	}
	
	private void showProgress(long algorithmID, long time) {
		Long[] array = new Long[2];
		array[0] = algorithmID;
		array[1] = time;
		publishProgress(array);
	}
	
	// Algorithm 1, for loop doing nothing 
	private void forloop () {
		showProgress(3, 0);
		// Using System.nanoTime instead of System.getTimeInMillis is more accurate
		// according to http://stackoverflow.com/questions/1770010/how-do-i-measure-time-elapsed-in-java
		long startTime = System.nanoTime();
		
		long time = cforloop();
		
		long endTime = System.nanoTime();
		showProgress(3, endTime - startTime);
		showProgress(31, time);
	}

    // Algorithm 2, Random quicksort of a backwards sorted array of size 10000
	// If it wouldn't be random there is a high possibility of a stackoverflow
    private void sort() {
    	showProgress(4, 0);
		long startTime = System.nanoTime();
		csort();
        
        long endTime = System.nanoTime();
		showProgress(4, endTime - startTime);
    }
    
	public native long cforloop();
	public native void csort();
	
    static {
        System.loadLibrary("algorithms");
    }
}
