/*
 * Performance analysis
 * 
 * Doing the Java work for the OpenGL graphics, and calling the native methods
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

package com.tgummerer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

import android.opengl.GLSurfaceView;

public class DiagramView extends GLSurfaceView {


    public DiagramView(Context context) {
        super(context);
        setRenderer(new DiagramRenderer());
    }

    private class DiagramRenderer implements GLSurfaceView.Renderer {
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // Set the background frame color
            gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        }

        public void onDrawFrame(GL10 gl) {
            // Redraw background color
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);
        }
    }
}
