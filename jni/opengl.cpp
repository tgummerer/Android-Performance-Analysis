/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <jni.h>
#include "diagram.h"
#include <android/log.h>

extern "C" {

    static Diagram * d;

    void Java_com_tgummerer_DiagramView_nativeInit( JNIEnv * env ) 
    {
        d = new Diagram();
    }

    void Java_com_tgummerer_DiagramView_nativeResize( JNIEnv * env, jobject thiz, jint w, jint h )
    {
        d->resize(w, h);
    }

    void Java_com_tgummerer_DiagramView_nativeRender( JNIEnv * env )
    {
        d->render();
    }

    void Java_com_tgummerer_DiagramView_nativeDone( JNIEnv * env )
    {
        __android_log_print(ANDROID_LOG_DEBUG, "native", "nativeDone");  
        delete d;
    }
}
