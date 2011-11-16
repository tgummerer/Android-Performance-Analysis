#include <jni.h>
#include <stdlib.h> // For Random
#include <sys/time.h> // For exact time measurements (Source: http://stackoverflow.com/questions/1861294/how-to-calculate-execution-time-of-a-code-snippet-in-c) Doesn't currently work correctly

#define ARRAY_LENGTH 1000

void quicksort( int[], int, int );
void swap( int[], int, int );

/* The functions return the time the algorithm took to run */

long Java_com_tgummerer_Progress_cforloop( JNIEnv * env ) 
{

    clock_t start, finish;
    start = clock();

    int i;
    for(i = 0; i < 1000000; i++);

    finish = clock();

    return finish - start;
}

int Java_com_tgummerer_Progress_csort( JNIEnv * env ) 
{
    int arr[ARRAY_LENGTH];
    int i = 0;
    for (; i < ARRAY_LENGTH; i++) {
        arr[i] = ARRAY_LENGTH - 1;
    }

    // Initialize random values for random quicksort
    srand(time(NULL));

    quicksort(arr, 0, ARRAY_LENGTH - 1);
    return 0;
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
