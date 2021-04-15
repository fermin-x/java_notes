package com.fermin.dataStructuresAndAlgorithms.sort;

import java.util.Arrays;

/**
 * Bubble Sort algorithm in detail.
 * one of the most straightforward sorting algorithms,
 * the core idea is to keep swapping adjacent elements of an array until the collection is sorted.
 * Small items “bubble” to the top of the list.
 * <p>
 * it is stable sort algorithm since the elements with the same value appear in the same order in the output list
 * as they appear in the original list.
 * <p>
 * Complexity
 * For the average and the worst case, the time complexity is O(n^2).
 * Worst and Average case: O(n*n), when the array is in reverse order
 * Best case: O(n), when the array is already sorted
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        //using the Java 8 approach to implement Bubble sort
        /*
        int n = arr.length;
        IntStream.range(0, n - 1)
                .flatMap(i -> IntStream.range(1, n - i))
                .forEach(j -> {
                    if (arr[j - 1] > arr[j]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                    }
                });
        */

        int i = 0, n = arr.length;
        boolean swapNeeded = true;
        //we can skip all remaining iterations since there is no need to swap any pair of adjacent elements
        while (i < n - 1 && swapNeeded) {
            //by default
            swapNeeded = false;
            //always from bottom to top, bubble the bigger item to the top
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapNeeded = true;
                }
            }
            if (!swapNeeded) {
                break;
            }
            i++;
        }
    }
}
