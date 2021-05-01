package com.ogabek.algorithms.sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SortingAlgorithm mergeSort = new MergeSort();
        System.out.println(Arrays.toString(mergeSort.sort(new int[]{1, 9, 8, 3, 5, 6, 7, 4, 2})));
    }
}
