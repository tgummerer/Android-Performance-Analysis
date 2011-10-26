#include <jni.h>
#include <stdio.h>
#include <stdlib.h> // For Random

#define ARRAY_LENGTH 100000

void quicksort( int[], int, int );
void swap( int[], int, int );

/* The functions return the time the algorithm took to run */

int Java_com_tgummerer_CAlgorithms_cforloop( JNIEnv * env ) 
{
    int i;
    for(i = 0; i < 100000; i++);
    return 0;
}

int Java_com_tgummerer_CAlgorithms_csort( JNIEnv * env ) 
{
    int arr[ARRAY_LENGTH];
    int i = 0;
    for (; i < ARRAY_LENGTH; i++) {
        arr[i] = ARRAY_LENGTH - 1;
    }

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
