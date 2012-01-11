
/*
 * MemTestDiagram drawing
 * 
 * Native OpenGL
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#include "memtestdiagram.h"
#include "sqlite3.h"
#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>

MemTestDiagram::MemTestDiagram()
{
    o = new OpenGL();
    c = new Connection();
}

MemTestDiagram::~MemTestDiagram()
{
    delete o;
    delete c;
}

void MemTestDiagram::resize(int w, int h)
{
    o->resize(w, h);
}

void MemTestDiagram::render()
{
    o->prepareFrame();
    this->drawAxis();
    this->drawJavaLine();
    this->drawCLine();
}

void MemTestDiagram::drawAxis()
{
    o->setRGBColor(0, 0, 0, 0);
    o->drawLine(0.85, -0.9, 0.85, 0.9);
    o->drawLine(-0.9, -0.85, 0.9, -0.85);
} 

void MemTestDiagram::drawJavaLine()
{
    const char maxquery[60] = "select max(memusage) from memtests";
    sqlite3_stmt * pstmt = c->prepare(maxquery);
    int maxmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        maxmem = atoi((char *)sqlite3_column_text(pstmt, 0));
    sqlite3_finalize(pstmt);

    const char minquery[60] = "select min(memusage) from memtests";
    pstmt = c->prepare(minquery);
    int minmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        minmem = atoi((char *)sqlite3_column_text(pstmt, 0));
    sqlite3_finalize(pstmt);

    maxmem -= minmem;

    const char countquery[60] = "select count(*) from memtests where type = 0";
    pstmt = c->prepare(countquery);
    int steps = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        steps = atoi((char *)sqlite3_column_text(pstmt, 0));
    sqlite3_finalize(pstmt);

    const char selectquery[200] = "select memusage from memtests where type = 0";
    pstmt = c->prepare(selectquery);
    int prevmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        prevmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    prevmem -= minmem;
    float stepwidth = ((this->val(LEFT) + this->val(RIGHT) - 2 * BORDERS)) / (steps);
    
    int i = 0;
    for (; i < steps - 1; ++i)
    {
        if(!(sqlite3_step(pstmt) == SQLITE_ROW))
            break; // Should never happen, just for safety

        int memusage = atoi((char *)sqlite3_column_text(pstmt, 0));
        memusage -= minmem;
        o->setRGBColor(120, 120, 255, 0);

        o->drawLine(((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * prevmem) + BOTTOM - BORDERS, (float)(i) * stepwidth + LEFT + BORDERS, ((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * memusage) + BOTTOM - BORDERS, (float)(i + 1) * stepwidth + LEFT + BORDERS);
        prevmem = memusage;
    }
    sqlite3_finalize(pstmt);
}

void MemTestDiagram::drawCLine() 
{
    const char maxquery[60] = "select max(memusage) from memtests";
    sqlite3_stmt * pstmt = c->prepare(maxquery);
    int maxmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        maxmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char minquery[60] = "select min(memusage) from memtests";
    pstmt = c->prepare(minquery);
    int minmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        minmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    maxmem -= minmem;

    const char countquery[60] = "select count(*) from memtests where type = 1";
    pstmt = c->prepare(countquery);
    int steps = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        steps = atoi((char *)sqlite3_column_text(pstmt, 0));

    const char selectquery[200] = "select memusage from memtests where type = 1";
    pstmt = c->prepare(selectquery);
    int prevmem = 0;
    if (sqlite3_step(pstmt) == SQLITE_ROW)
        prevmem = atoi((char *)sqlite3_column_text(pstmt, 0));

    prevmem -= minmem;
    float stepwidth = ((this->val(LEFT) + this->val(RIGHT) - 2 * BORDERS)) / (steps);
    
    int i = 0;
    for (; i < steps - 1; ++i)
    {
        if(!(sqlite3_step(pstmt) == SQLITE_ROW))
            break; // Should never happen, just for safety

        int memusage = atoi((char *)sqlite3_column_text(pstmt, 0));
        memusage -= minmem;
        o->setRGBColor(255, 120, 120, 0);

        o->drawLine(((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * prevmem) + BOTTOM - BORDERS, (float)(i) * stepwidth + LEFT + BORDERS, ((float)(MAX_HEIGHT + BORDERS * 2) / maxmem * memusage) + BOTTOM - BORDERS, (float)(i + 1) * stepwidth + LEFT + BORDERS);
        prevmem = memusage;
    }
}

float MemTestDiagram::val(float f)
{
    if (f < 0)
        return -f;
    return f;
}
