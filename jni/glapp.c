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
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
}

void appInit()
{
    glEnable(GL_NORMALIZE);
    // The diagram will be in 2D, thus no depth test needed
    glDisable(GL_DEPTH_TEST);
    glEnableClientState(GL_VERTEX_ARRAY);
}

void appRender(int w, int h)
{
    // Prepare OpenGL ES for rendering of the frame.
    prepareFrame(w, h);
    GLfloat vertices[] = {0.5,0, -0.5,1, 0.5,1, -0.5,0};
    glColor4f(0.63671875f, 0.76953125f, 0.22265625f, 0.0f);
    glVertexPointer(2, GL_FLOAT, 0, vertices);
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
}

