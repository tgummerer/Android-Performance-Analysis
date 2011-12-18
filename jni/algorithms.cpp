extern "C" {
    #include <jni.h>
    #include <cstdlib> // For Random
    #include <ctime>
    #include "classtest.h"

    #define ARRAY_LENGTH 1000

    void quicksort( int[], int, int );
    void swap( int[], int, int );

    /* The functions return the time the algorithm took to run */

    void Java_com_tgummerer_Progress_cforloop( JNIEnv * env ) 
    {

        clock_t start, finish;
        int i;
        for(i = 0; i < 1000000; i++);
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
        if (n == 1)
            return 1;
        return recursive(n - 1);
    }

    void Java_com_tgummerer_Progress_crecursive( JNIEnv * env, int n )
    {
        recursive(n);
        return;
    }

    void Java_com_tgummerer_Progress_cclassestest( JNIEnv * env )
    {
        Chain * chain = new Chain(40);
        chain->kill(3);
        delete chain;
    }
}
