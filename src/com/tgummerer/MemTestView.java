/*
 * Performance analysis
 * 
 * Show the memory test diagram
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class MemTestView extends GLSurfaceView {


    public MemTestView(Context context) {
        super(context);
        setRenderer(new DiagramRenderer());
    }

    private class DiagramRenderer implements GLSurfaceView.Renderer {

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            nativeInit();
        }

        public void onDrawFrame(GL10 gl) {
            nativeRender();
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            nativeResize(width, height);
        }

    }

    private static native void nativeInit();
    private static native void nativeResize(int w, int h);
    private static native void nativeRender();
    private static native void nativeDone();

    {
        System.loadLibrary("memtestopengl");
    }
}
