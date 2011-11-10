/*
 * Performance analysis
 * 
 * Native OpenGL, the corresponding c file should do most of the OpenGL work?? TODO
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */
#include "glapp.h"
void prepareFrame(int w, int h)
{
    glViewport(0, 0, w, h);

    glClearColorx((GLfixed)(0.1f * 65536),
                  (GLfixed)(0.2f * 65536),
                  (GLfixed)(0.3f * 65536), 0x10000);
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
}


void appInit()
{
    glEnable(GL_NORMALIZE);
    glEnable(GL_DEPTH_TEST);
}

void appRender(int w, int h)
{
    // Prepare OpenGL ES for rendering of the frame.
    prepareFrame(w, h);
}

