/*
 * Diagram drawing
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#ifndef DIAGRAM_H
#define DIAGRAM_H

#include "gllib.h"
#include "connection.h"
#include <ft2build.h>
#include FT_FREETYPE_H 

#define LEFT       -0.85
#define RIGHT       0.85

#define BOTTOM      0.85
#define MAX_HEIGHT -1.7

#define DISTANCE    0.02

class Diagram {
    private:
        OpenGL * o;
        Connection * c;
        void drawAxis();
        void drawBars();
        void drawLegend();
        float val(float);
    public:
        Diagram();
        ~Diagram();
        void resize(int, int);
        void render();
};

#endif
