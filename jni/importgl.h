/*
 * Performance analysis
 * 
 * Importing opengl, taken from san-angeles demo in android ndk.
 * 
 * Copyright (c) Thomas Gummerer 2011 | All rights reserved
 */

#ifndef IMPORTGL_H
#define IMPORTGL_H

#include <GLES/gl.h>

extern int importGLInit();

extern void importGLDeinit();

#ifndef IMPORTGL_API
#define IMPORTGL_API extern
#endif
#ifndef IMPORTGL_FNPTRINIT
#define IMPORTGL_FNPTRINIT
#endif

#define FNDEF(retType, funcName, args) IMPORTGL_API retType (*funcPtr_##funcName) args IMPORTGL_FNPTRINIT

FNDEF(void, glViewport, (GLint x, GLint y, GLsizei width, GLsizei height));

#undef FN
#define FNPTR(name) funcPtr_##name

#ifndef IMPORTGL_NO_FNPTR_DEFS
#define glViewport              FNPTR(glViewport)
#endif

#endif
