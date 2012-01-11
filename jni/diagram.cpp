/*
 * Diagram drawing
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include "diagram.h"
#include "sqlite3.h"
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>

Diagram::Diagram()
{
    o = new OpenGL();
    c = new Connection();
}

Diagram::~Diagram()
{
    delete o;
    delete c;
}

void Diagram::resize(int w, int h)
{
    o->resize(w, h);
}

void Diagram::render()
{
    o->prepareFrame();
    this->drawAxis();
    this->drawBars();
    this->drawLegend();
}

void Diagram::drawAxis()
{
    o->setRGBColor(0, 0, 0, 0);
    o->drawLine(0.85, -0.9, 0.85, 0.9);
    o->drawLine(-0.9, -0.85, 0.9, -0.85);
} 

void Diagram::drawBars()
{
    const char countquery[35] = "select count(*) from measurements";
    sqlite3_stmt * pstmt = c->prepare(countquery);
    int nrtests = -1;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        nrtests = atoi((char *)sqlite3_column_text(pstmt, 0));

    // Avoid divisions by 0
    if (nrtests == 0)
        nrtests = -1;

    int maxtime;
    if (nrtests != -1) {
        const char maxquery[60] = "select max(time) from measurements";
        pstmt = c->prepare(maxquery);
        maxtime = -1;
        if (sqlite3_step(pstmt) == SQLITE_ROW) 
            maxtime = atoi((char *)sqlite3_column_text(pstmt, 0));

        if (maxtime == 0)
            maxtime = -1;
    } else {
        maxtime = -1;
    }

    const char selectquery[200] = "select langid, algorithmid, time from measurements order by algorithmid desc, langid asc";
    pstmt = c->prepare(selectquery);

    float barwidth = ((this->val(LEFT) + this->val(RIGHT)) - (nrtests + nrtests / 2) * DISTANCE) / (nrtests / 2);
    
    int i = 0;
    for (; i < nrtests; ++i)
    {
        if(!(sqlite3_step(pstmt) == SQLITE_ROW))
            break; // Should never happen, just for safety
        int langid = atoi((char *)sqlite3_column_text(pstmt, 0));
        if (langid == 1)
            o->setRGBColor(120, 120, 255, 0);
        else
            o->setRGBColor(255, 120, 120, 0);

        o->drawRectangle(BOTTOM, RIGHT - ((float)(i + 3 - langid * 2) * DISTANCE + ((i / 2) + 1) * barwidth), MAX_HEIGHT / maxtime * atoi((char *)sqlite3_column_text(pstmt, 2)), barwidth);

    }
    //sqlite3_finalize(pstmt);
}

void Diagram::drawLegend()
{
    o->setRGBColor(255, 120, 120, 0);
    o->drawRectangle(BOTTOM + MAX_HEIGHT, LEFT + 0.1, 0.1, 0.065);

    // Draw a J for Java
    o->setRGBColor(0, 0, 0, 0);
    o->drawLine(BOTTOM + MAX_HEIGHT, LEFT + 0.2, BOTTOM + MAX_HEIGHT, LEFT + 0.25);
    o->drawLine(BOTTOM + MAX_HEIGHT, LEFT + 0.25, BOTTOM + MAX_HEIGHT + 0.075, LEFT + 0.25);
    o->drawLine(BOTTOM + MAX_HEIGHT + 0.075, LEFT + 0.25, BOTTOM + MAX_HEIGHT + 0.1, LEFT + 0.225);
    o->drawLine(BOTTOM + MAX_HEIGHT + 0.1, LEFT + 0.225, BOTTOM + MAX_HEIGHT + 0.075, LEFT + 0.2);

    o->setRGBColor(120, 120, 255, 0);
    o->drawRectangle(BOTTOM + MAX_HEIGHT + 0.2, LEFT + 0.1, 0.1, 0.065);
    
    // Draw a C
    o->setRGBColor(0, 0, 0, 0);
    o->drawLine(BOTTOM + MAX_HEIGHT + 0.2, LEFT + 0.2, BOTTOM + MAX_HEIGHT + 0.2, LEFT + 0.25);
    o->drawLine(BOTTOM + MAX_HEIGHT + 0.2, LEFT + 0.2, BOTTOM + MAX_HEIGHT + 0.3, LEFT + 0.2);
    o->drawLine(BOTTOM + MAX_HEIGHT + 0.3, LEFT + 0.2, BOTTOM + MAX_HEIGHT + 0.3, LEFT + 0.25);
}

float Diagram::val(float f)
{
    if (f < 0)
        return -f;
    return f;
}
