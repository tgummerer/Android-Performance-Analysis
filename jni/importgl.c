#include <stdlib.h>
#include <dlfcn.h>
static void *sGLESSO = NULL;

#define IMPORTGL_NO_FNPTR_DEFS
#define IMPORTGL_API
#define IMPORTGL_FNPTRINIT = NULL
#include "importgl.h"


int importGLInit()
{
    #undef IMPORT_FUNC
    int result = 1;
    sGLESSO = dlopen("libGLESv1_CM.so", RTLD_NOW);
    if (sGLESSO == NULL)
        return 0;   // Cannot find OpenGL ES Common or Common Lite SO.

#define IMPORT_FUNC(funcName) do { \
    void *procAddress = (void *)dlsym(sGLESSO, #funcName); \
    if (procAddress == NULL) result = 0; \
    *((void **)& FNPTR(funcName)) = procAddress; } while (0)

    IMPORT_FUNC(glViewport);
    return result;
}
