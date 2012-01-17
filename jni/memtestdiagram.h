/*
 * Diagram drawing
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#ifndef MEMTESTDIAGRAM_H
#define MEMTESTDIAGRAM_H

#include "gllib.h"
#include "connection.h"

#define LEFT       -0.85
#define RIGHT       0.85

#define BOTTOM      0.85
#define MAX_HEIGHT -1.7

#define BORDERS 0.05

class MemTestDiagram {
    private:
        OpenGL * o;
        Connection * c;
        void drawAxis();
        void drawJavaLine();
        void drawCLine();
        void drawLegend();
        float val(float);
    public:
        MemTestDiagram();
        ~MemTestDiagram();
        void resize(int, int);
        void render();
};

#endif
