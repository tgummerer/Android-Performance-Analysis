/*
 * Performance analysis
 * 
 * Native OpenGL, the corresponding c file should do most of the OpenGL work?? TODO
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#ifndef GLLIB_H
#define GLLIB_H

#include <GLES/gl.h>

extern int importGLInit();
extern int importGLDeinit();

class OpenGL {
    private:
        int windowWidth;
        int windowHeight;
     public:
        OpenGL();
        ~OpenGL();
        void appInit();
        void resize(int, int);
        void prepareFrame();
        void drawRectangle(float, float, float, float);
        void setRGBColor(int, int, int, float);
        void drawLine(float, float, float, float);
        void render();
};

#endif
