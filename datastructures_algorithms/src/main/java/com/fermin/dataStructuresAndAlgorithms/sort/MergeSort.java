package com.fermin.dataStructuresAndAlgorithms.sort;

/**
 * Merge sort is a “divide and conquer” algorithm wherein we first divide the problem into subproblems.
 * When the solutions for the subproblems are ready, we combine them together to get the final solution to the problem.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] actual = {5, 1, 6, 2, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6};
        MergeSort.mergeSort(actual, actual.length);
//        assertArrayEquals(expected, actual);
    }

    /**
     * it can be described as the following 2 step process:
     * Divide: In this step, we divide the input array into 2 halves,
     * the pivot will be the midpoint of the array.
     * Conquer: In this step, we sort and merge the divided arrays from bottom to top and get the sorted array.
     *
     * @param a
     * @param n
     */
    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        // method called recursively to divide arrays into tow sub-arrays
        mergeSort(l, mid);
        mergeSort(r, n - mid);


        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
