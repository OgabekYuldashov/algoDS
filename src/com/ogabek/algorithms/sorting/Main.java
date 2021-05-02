package com.ogabek.algorithms.sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Insertion Sort:");
        SortingAlgorithm insertionSort = new InsertionSort();
        System.out.println(Arrays.toString(insertionSort.sort(new int[]{1,2,3,4,5,6,7,8,9,0})));
        System.out.println(Arrays.toString(insertionSort.sort(new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0})));

        System.out.println("\nMerge Sort:");
        SortingAlgorithm mergeSort = new MergeSort();
        System.out.println(Arrays.toString(mergeSort.sort(new int[]{1, 9, 8, 3, 5, 6, 7, 4, 2})));

        System.out.println("\nQuick Sort:");
        SortingAlgorithm quickSort = new QuickSort();
        System.out.println(Arrays.toString(quickSort.sort(new int[]{1, 9, 8, 3, 5, 6, 7, 4, 2})));
        System.out.println(Arrays.toString(quickSort.sort(new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0})));

    }
}
