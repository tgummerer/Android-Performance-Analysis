/*
 * MonitorDiagram drawing
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include "monitordiagram.h"
#include "sqlite3.h"
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>

MonitorDiagram::MonitorDiagram()
{
    o = new OpenGL();
    c = new Connection();
}

MonitorDiagram::~MonitorDiagram()
{
    delete o;
    delete c;
}

void MonitorDiagram::resize(int w, int h)
{
    o->resize(w, h);
}

void MonitorDiagram::render()
{
    o->prepareFrame();
    this->drawAxis();
    this->drawBars();
}

void MonitorDiagram::drawAxis()
{
    o->setRGBColor(0, 0, 0, 0);
    o->drawLine(0.85, -0.9, 0.85, 0.9);
    o->drawLine(-0.9, -0.85, 0.9, -0.85);
} 

void MonitorDiagram::drawBars()
{
    const char maxquery[60] = "select max(memusage) from monitor";
    sqlite3_stmt * pstmt = c->prepare(maxquery);
    int maxmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        maxmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char minquery[60] = "select min(memusage) from monitor";
    pstmt = c->prepare(minquery);
    int minmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        minmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    maxmem -= minmem;

    const char countquery[60] = "select count(*) from monitor";
    pstmt = c->prepare(countquery);
    int steps = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        steps = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char selectquery[200] = "select memusage from monitor";
    pstmt = c->prepare(selectquery);
    int prevmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        prevmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    prevmem -= minmem;
    float stepwidth = ((this->val(LEFT) + this->val(RIGHT) - 2 * BORDERS)) / (steps);
    
    int i = 0;
    for (; i < steps - 1; ++i)
    {
        if(!sqlite3_step(pstmt) == SQLITE_ROW)
            break; // Should never happen, just for safety

        int memusage = atoi((char *)sqlite3_column_text(pstmt, 0));
        memusage -= minmem;
        o->setRGBColor(120, 120, 255, 0);

        o->drawLine(((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * prevmem) + BOTTOM - BORDERS, (float)(i) * stepwidth + LEFT + BORDERS, ((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * memusage) + BOTTOM - BORDERS, (float)(i + 1) * stepwidth + LEFT + BORDERS);
        prevmem = memusage;
    }
}

float MonitorDiagram::val(float f)
{
    if (f < 0)
        return -f;
    return f;
}
