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
    int nrtests = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        nrtests = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char maxquery[60] = "select max(time) from measurements";
    pstmt = c->prepare(maxquery);
    int maxtime = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        maxtime = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char selectquery[200] = "select langid, algorithmid, time from measurements order by algorithmid desc, langid asc";
    pstmt = c->prepare(selectquery);

    float barwidth = ((this->val(LEFT) + this->val(RIGHT)) - (nrtests + nrtests / 2) * DISTANCE) / (nrtests / 2);
    
    int i = 0;
    for (; i < nrtests; ++i)
    {
        if(!sqlite3_step(pstmt) == SQLITE_ROW)
            break; // Should never happen, just for safety
        int langid = atoi((char *)sqlite3_column_text(pstmt, 0));
        if (langid == 1)
            o->setRGBColor(120, 120, 255, 0);
        else
            o->setRGBColor(255, 120, 120, 1);

        o->drawRectangle(BOTTOM, LEFT + (float)(i + 1) * DISTANCE + (i / 2) * barwidth, MAX_HEIGHT / maxtime * atoi((char *)sqlite3_column_text(pstmt, 2)), barwidth);

    }
}

float Diagram::val(float f)
{
    if (f < 0)
        return -f;
    return f;
}
