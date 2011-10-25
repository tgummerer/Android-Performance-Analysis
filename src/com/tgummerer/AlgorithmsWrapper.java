package com.tgummerer;

public class AlgorithmsWrapper {
	public native void startCAlgorithms();
	
    static {
        System.loadLibrary("algorithms");
    }
}
