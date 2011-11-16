/*
 * Performance analysis
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */
#include "glapp.h"

OpenGL::OpenGL()
{
    importGLInit();
    this->windowWidth = 320;
    this->windowHeight = 480;
    this->appInit();
}

OpenGL::~OpenGL()
{
    importGLDeinit();
}

void OpenGL::appInit()
{
    glEnable(GL_NORMALIZE);
    // The diagram will be in 2D, thus no depth test needed
    glDisable(GL_DEPTH_TEST);
    glEnableClientState(GL_VERTEX_ARRAY);
}

void OpenGL::resize(int w, int h)
{
    windowWidth = w;
    windowHeight = h;
}

void OpenGL::prepareFrame()
{
    glViewport(0, 0, windowWidth, windowHeight);
    glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
}

void OpenGL::render()
{
    // Prepare OpenGL ES for rendering of the frame.
    this->prepareFrame();
    glColor4f(0.63671875f, 0.76953125f, 0.22265625f, 0.0f);
    this->drawRectangle(-0.5, 0, 1, 1);
}

// Attention, x, height go from left to right, and y from top to bottom, since diagram
// will be displayed for a rotated phon
void OpenGL::drawRectangle(float x, float y, float height, float width)
{
    GLfloat vertices[] = {x+height,y, x,y+width, x+height,y+width, x+height,y, x,y, x,y+width };
    glVertexPointer(2, GL_FLOAT, 0, vertices);
    glDrawArrays(GL_TRIANGLES, 0, 6);
}
