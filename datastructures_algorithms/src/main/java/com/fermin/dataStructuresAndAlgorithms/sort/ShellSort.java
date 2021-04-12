package cn.fermin.dataStructuresAndAlgorithms.sort;

import java.util.Random;

/**
 * shellSort implementation in Java.
 * a kind of optimized insertSort
 * the algorithm divide the original set into small subsets and then each of them is sorted using Insertion sort.
 * shell sort has a unique way to breaks an original set into small sets
 * by using the so-called interval or gap for subset creation.
 * we should be careful not to choose too few or too many gaps.
 * More details can be found in {@link https://en.wikipedia.org/wiki/Shellsort#Gap_sequences}
 * <p>
 * Complexity
 * Generally, Shell sort algorithm is very efficient with medium-sized lists.
 * the complexity is difficulty to determine as it depends a lot on the gap sequence.
 * but the time complexity varies form O(N) to O(N^2).
 */
public class ShellSort {

    public static void main(String[] args) {
        /*
        int[] arr = {1, 9, 7, 5, 3, 2};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
        */

        //initiate an array with 80000-sized for test
        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = new Random().nextInt(80000);
        }

        //System.out.println(Arrays.toString(arr));
        long current = System.currentTimeMillis();
        shellSort2(arr);
        System.out.println(System.currentTimeMillis() - current);
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr the array to be sorted
     *            N/2, N/4, …, 1 (continuously dividing by 2)
     */
    public static void shellSort2(int[] arr) {
        int val = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                val = arr[j];
                if (arr[j] > arr[j - gap]) {
                    //insertion sort: find the position to insert
                    while (j - gap >= 0 && val > arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = val;
                }
            }
        }
    }
}
