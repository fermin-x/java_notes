package com.fermin.dataStructuresAndAlgorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Insertion Sort is an efficient algorithm for ordering a small number of items.
 * This method is based on the way card players sort a hand of playing cards.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {1, 9, 7, 5, 3, 2};
        insertionSortImperative(arr);
        //insertionSortRecursive(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[80000];
        long current = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            arr[i] = new Random().nextInt(80000);
        }
        insertionSortImperative(arr);
        System.out.println(System.currentTimeMillis() - current);

    }

    /**
     * Imperative Implementation
     *
     * @param arr
     */
    public static void insertionSortImperative(int[] arr) {

        //begin with the second card you get, since the first card you need to compare.
        for (int i = 1; i < arr.length; i++) {
            //current card you pick up
            int key = arr[i];
            int j = i - 1;
            //compare with the cards in your hands, find the correct position.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * recursive implementation
     *
     * @param arr
     */
//    public static void insertionSortRecursive(int[] arr) {
//        insertionSortRecursive(arr, arr.length);
//    }
//
//    private static void insertionSortRecursive(int[] input, int i) {
//        if (i <= 1) {
//            return;
//        }
//        insertionSortRecursive(input, i - 1);
//        int key = input[i - 1];
//        int j = i - 2;
//        while (j >= 0 && input[j] > key) {
//            input[j + 1] = input[j];
//            j = j - 1;
//        }
//        input[j + 1] = key;
//    }
}
