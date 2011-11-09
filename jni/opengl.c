/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <jni.h>
#include "glapp.h"

int   appAlive   = 1;

static int  windowWidth  = 320;
static int  windowHeight = 480;
static int  demoStopped  = 0;

void Java_com_tgummerer_DiagramView_nativeInit( JNIEnv * env ) 
{
    appInit();
    appAlive = 1;
}

void Java_com_tgummerer_DiagramView_nativeResize( JNIEnv * env, jobject thiz, jint w, jint h )
{
    windowWidth = w;
    windowHeight = h;
}

void Java_com_tgummerer_DiagramView_nativeRender( JNIEnv * env )
{
    appRender(windowWidth, windowHeight);
}
