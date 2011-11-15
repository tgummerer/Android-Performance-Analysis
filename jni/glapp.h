/*
 * Performance analysis
 * 
 * Native OpenGL, the corresponding c file should do most of the OpenGL work?? TODO
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#ifndef GLAPP_H
#define GLAPP_H

#include <GLES/gl.h>

extern int importGLInit();

void appInit();
void appRender(int, int);
void drawRectangle(float, float, float, float);

#endif
