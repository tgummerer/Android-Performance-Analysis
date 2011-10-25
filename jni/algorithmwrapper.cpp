#include <jni.h>
#include "algorithms.h"

extern "C" { // Needed because jni can only call C methods, no C++
    void Java_com_tgummerer_startCAlgorithms( JNIEnv * env ) {
        Algorithms * alg = new Algorithms();
    }
}
