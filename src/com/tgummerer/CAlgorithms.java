package com.tgummerer;

public class CAlgorithms{
	public native void cforloop();
	public native void csort();
    static {
        System.loadLibrary("algorithms");
    }
}
