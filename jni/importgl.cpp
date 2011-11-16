/*
 * Performance analysis
 * 
 * Importing opengl taken and simplified from the android-ndk san-angeles example
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */
#include <stdlib.h>
#include <dlfcn.h>
static void *sGLESSO = NULL;

#define IMPORTGL_NO_FNPTR_DEFS
#define IMPORTGL_API
#define IMPORTGL_FNPTRINIT = NULL
#include "importgl.h"

int importGLInit()
{
    #undef IMPORT_FUNC
    int result = 1;
    sGLESSO = dlopen("libGLESv1_CM.so", RTLD_NOW);
    if (sGLESSO == NULL)
        return 0;   // Cannot find OpenGL ES Common or Common Lite SO.

#define IMPORT_FUNC(funcName) do { \
    void *procAddress = (void *)dlsym(sGLESSO, #funcName); \
    if (procAddress == NULL) result = 0; \
    *((void **)& FNPTR(funcName)) = procAddress; } while (0)

    IMPORT_FUNC(glBlendFunc);
    IMPORT_FUNC(glClear);
    IMPORT_FUNC(glClearColorx);
    IMPORT_FUNC(glColor4x);
    IMPORT_FUNC(glColorPointer);
    IMPORT_FUNC(glDisable);
    IMPORT_FUNC(glDisableClientState);
    IMPORT_FUNC(glDrawArrays);
    IMPORT_FUNC(glEnable);
    IMPORT_FUNC(glEnableClientState);
    IMPORT_FUNC(glFrustumx);
    IMPORT_FUNC(glGetError);
    IMPORT_FUNC(glLightxv);
    IMPORT_FUNC(glLoadIdentity);
    IMPORT_FUNC(glMaterialx);
    IMPORT_FUNC(glMaterialxv);
    IMPORT_FUNC(glMatrixMode);
    IMPORT_FUNC(glMultMatrixx);
    IMPORT_FUNC(glNormalPointer);
    IMPORT_FUNC(glPopMatrix);
    IMPORT_FUNC(glPushMatrix);
    IMPORT_FUNC(glRotatex);
    IMPORT_FUNC(glScalex);
    IMPORT_FUNC(glShadeModel);
    IMPORT_FUNC(glTranslatex);
    IMPORT_FUNC(glVertexPointer);
    IMPORT_FUNC(glViewport);
    return result;
}

void importGLDeinit()
{
    dlclose(sGLESSO);
}
