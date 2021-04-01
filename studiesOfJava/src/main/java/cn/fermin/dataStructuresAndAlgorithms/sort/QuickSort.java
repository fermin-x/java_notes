package cn.fermin.dataStructuresAndAlgorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * quick sort is leveraging the divide-and-conquer principle.
 * It's important to remember that Quicksort isn't stable algorithm.
 * The input list is divided into two sub-list by an element called pivot;
 * This process repeats for sub-list.
 * <p>
 * The crucial point in Quicksort is to choose the best pivot.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 9, 7, 5, 8, 8, 8, 3, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        arr = new int[80000];
        long current = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            arr[i] = new Random().nextInt(80000);
        }
        quickSort(arr, 0, arr.length - 1);
        System.out.println(System.currentTimeMillis() - current);
    }

    private static void quickSort(int[] src, int begin, int end) {
        if (begin < end) {
            int pivot = src[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                //from the end of the list, find the element which is smaller then the pivot.
                while (i < j && src[j] > pivot) {
                    j--;
                }
                if (i < j) {
                    src[i] = src[j];
                    i++;
                }
                //
                while (i < j && src[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    src[j] = src[i];
                    j--;
                }
            }
            src[i] = pivot;
            quickSort(src, begin, i - 1);
            quickSort(src, i + 1, end);
        }
    }
}
