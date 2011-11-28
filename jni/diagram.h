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

class Diagram {
    private:
        OpenGL * o;
        Connection * c;
        void drawAxis();
        void drawBars();
    public:
        Diagram();
        ~Diagram();
        void resize(int, int);
        void render();
};

#endif
