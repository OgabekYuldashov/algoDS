package com.ogabek.algorithms.sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Merge Sort:");
        SortingAlgorithm mergeSort = new MergeSort();
        System.out.println(Arrays.toString(mergeSort.sort(new int[]{1, 9, 8, 3, 5, 6, 7, 4, 2})));

        System.out.println("\nQuick Sort:");
        SortingAlgorithm quickSort = new QuickSort();
        System.out.println(Arrays.toString(quickSort.sort(new int[]{1, 9, 8, 3, 5, 6, 7, 4, 2})));
        System.out.println(Arrays.toString(quickSort.sort(new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0})));

    }
}
