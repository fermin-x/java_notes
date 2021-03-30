package cn.fermin.dataStructuresAndAlgorithms.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * selection sort begins with the first element
 * and scan through subsequent elements to find the smallest element.
 * Once found, the smallest element is swapped with the element in the first position.
 * The algorithm then removes on to the element in the second position.
 * <p>
 * COMPLEXITY
 * this algorithm have a time complexity of O(n^2).
 * SPACE
 * Selection Sort requires one extra variable to hold the value temporarily for swapping.
 * Therefore, Selection Sort's space complexity is O(1).
 * <p>
 * <p>
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1, 9, 7, 5, 3, 2};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[80000];
        long current = System.currentTimeMillis();
        for (int i = 0; i < 80000; i++) {
            arr[i] = new Random().nextInt(80000);
        }
        selectSort(arr);
        System.out.println(System.currentTimeMillis() - current);

    }

    public static void selectSort(int[] arr) {
        //selecting the smallest element required a total of (n-1) comparisons
        int i = 0;
        int n = arr.length - 1;
        while (i < n) {
            //assume the smallest element
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minElementIndex] > arr[j]) {
                    minElementIndex = j;
                }
            }

            if (minElementIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minElementIndex];
                arr[minElementIndex] = temp;
            }

            i++;
        }
    }

    /**
     * recursive implementation
     *
     * @param arr
     */
//    public static void selectSortRecursive(int[] arr) {
//        selectSortRecursive(arr, arr.length);
//    }
//
//    public static void selectSortRecursive(int[] arr, int index) {
//        if (index <= 1) {
//            return;
//        }
//        selectSortRecursive(arr, index - 1);
//        int key = arr[index - 1];
//        int sanIndex = index - 2;
//        int smallIndex = index - 1;
//        while (sanIndex >= 0) {
//            if (arr[sanIndex] < key) {
//                key = arr[sanIndex];
//                smallIndex = sanIndex;
//            }
//
//            sanIndex--;
//        }
//
//        int temp = arr[index - 1];
//        arr[index - 1] = arr[smallIndex];
//        arr[smallIndex] = temp;
//
//    }

}
