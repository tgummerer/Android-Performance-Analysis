/*
 * Performance analysis
 * 
 * Wrapper for performing the jni method calls.
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

public class DiagramLib {

    public static native void init(int width, int height);
    public static native void step();

    static {
        System.loadLibrary("opengl");
    }
}
