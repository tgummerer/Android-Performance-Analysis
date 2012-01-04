/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include <jni.h>
#include "monitordiagram.h"

extern "C" {

    static MonitorDiagram * d;

    void Java_com_tgummerer_MonitorDiagramView_nativeInit( JNIEnv * env ) 
    {
        d = new MonitorDiagram();
    }

    void Java_com_tgummerer_MonitorDiagramView_nativeResize( JNIEnv * env, jobject thiz, jint w, jint h )
    {
        d->resize(w, h);
    }

    void Java_com_tgummerer_MonitorDiagramView_nativeRender( JNIEnv * env )
    {
        d->render();
    }

    void Java_com_tgummerer_MonitorDiagramView_nativeDone( JNIEnv * env )
    {
        delete d;
    }
}
