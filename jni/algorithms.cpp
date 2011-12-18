extern "C" {
    #include <jni.h>
    #include <cstdlib> // For Random
    #include <ctime>
    #include "classtest.h"

    #define ARRAY_LENGTH 500

    void quicksort( int[], int, int );
    void swap( int[], int, int );

    /* The functions return the time the algorithm took to run */

    float Java_com_tgummerer_Progress_cforloop( JNIEnv * env ) 
    {

        int i;
        float k = 100000;
        for(i = 0; i < 100000; i++)
            k = k / i;
        return k;
    }

    void  Java_com_tgummerer_Progress_csort( JNIEnv * env ) 
    {
        int arr[ARRAY_LENGTH];
        int i = 0;
        for (; i < ARRAY_LENGTH; i++) {
            arr[i] = ARRAY_LENGTH - 1;
        }

        // Initialize random values for random quicksort
        srand(time(NULL));

        quicksort(arr, 0, ARRAY_LENGTH - 1);
    }

    void quicksort( int arr[], int start, int end ) 
    {
        int i = start;
        int k = end;

        if (end - start >= 1) {
            int p = rand() % (end - start);
            int pivot = arr[p];
            while (k > i) {
                while (arr[i] <= pivot && i <= end && k > i)
                    i++;
                while (arr[k] > pivot && k >= start && k >= i)
                    k--;
                if (k > i)
                    swap(arr, i, k);
            }
            swap(arr, start, k);

            quicksort(arr, start, k - 1);
            quicksort(arr, k + 1, end);
        } else 
            return;
    }

    void swap( int arr[], int i, int k ) {
        int t = arr[i];
        arr[i] = arr[k];
        arr[k] = t;
    }

    // Extra method call, to avoid passing the pointer to the JNIEnv each time.
    // May be only a small overhead, but may be quite significant
    long recursive( long n ) {
        if (n < 2)
            return 1;
        return recursive(n - 1) + recursive(n - 2);
    }

    void Java_com_tgummerer_Progress_crecursive( JNIEnv * env )
    {
        recursive(19);
        return;
    }

    void Java_com_tgummerer_Progress_cclassestest( JNIEnv * env )
    {
        Chain * chain = new Chain(500);
        chain->kill(250);
        delete chain;
    }
}
