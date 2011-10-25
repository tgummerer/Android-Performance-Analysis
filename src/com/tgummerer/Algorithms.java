package com.tgummerer;

import java.util.Random;

public class Algorithms {
	private Progress progress = null;
	
	public Algorithms(Progress progress) {
		this.progress = progress;
	}
	
	public void startAlgorithms () {
		forloop();
        sort();
	}
	
	// Algorithm 1, for loop doing nothing 
	private void forloop () {
		for (int i = 0; i < 10000; i++);
	}

    // Algorithm 2, Random quicksort of a backwards sorted array of size 10000
	// If it wouldn't be random there is a high possibility of a stackoverflow
    private void sort() {
        int [] arr = new int[100000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = arr.length - i;

        quicksort(arr, 0, arr.length - 1);
    }

    private void quicksort(int arr[], int start, int end) {
        int i = start;
        int k = end;
        Random r = new Random();

        if (end - start >= 1) {
            int p = r.nextInt(end - start);
            p = p + start;
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

    private void swap(int arr[], int i, int k) {
        int t = arr[i];
        arr[i] = arr[k];
        arr[k] = t;
    }
}
