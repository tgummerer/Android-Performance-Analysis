/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <jni.h>
#include "memtestdiagram.h"

extern "C" {

    static MemTestDiagram * d;

    void Java_com_tgummerer_MemTestView_nativeInit( JNIEnv * env ) 
    {
        d = new MemTestDiagram();
    }

    void Java_com_tgummerer_MemTestView_nativeResize( JNIEnv * env, jobject thiz, jint w, jint h )
    {
        d->resize(w, h);
    }

    void Java_com_tgummerer_MemTestView_nativeRender( JNIEnv * env )
    {
        d->render();
    }

    void Java_com_tgummerer_MemTestView_nativeDone( JNIEnv * env )
    {
        delete d;
    }
}
