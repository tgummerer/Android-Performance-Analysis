/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <jni.h>
#include "glapp.h"

extern "C" {

    static OpenGL * o;

    void Java_com_tgummerer_DiagramView_nativeInit( JNIEnv * env ) 
    {
        o = new OpenGL();
    }

    void Java_com_tgummerer_DiagramView_nativeResize( JNIEnv * env, jobject thiz, jint w, jint h )
    {
        o->resize(w, h);
    }

    void Java_com_tgummerer_DiagramView_nativeRender( JNIEnv * env )
    {
        o->render();
    }

    void Java_com_tgummerer_DiagramView_nativeDone( JNIEnv * env )
    {
        delete o;
    }
}
