package com.ogabek.interview_questions.miscellanous;

import java.util.Arrays;

/**
 * Given two sorted integer arrays, produce a new array
 * combining both given arrays in a sorted order
 */
public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] result = mergeSortedArrays(new int[]{0,3,4,31}, new int[] {});
        Arrays.stream(result).forEach(System.out::println);
    }

    /**
     * @param array1 sorted integer array1
     * @param array2 sorted integer array2
     * @return merged array in sorted order
     *
     * RUNNING TIME: O(n+m)
     */
    private static int[] mergeSortedArrays(int[] array1, int[] array2) {
        int[] merged = new int[array1.length + array2.length];

        int arr1Index = array1.length-1;
        int arr2Index = array2.length-1;
        int i = merged.length - 1;

        while (arr1Index >= 0 && arr2Index >= 0) {
            if (array1[arr1Index] > array2[arr2Index]) {
                merged[i] = array1[arr1Index];
                arr1Index--;
            } else {
                merged[i] = array2[arr2Index];
                arr2Index--;
            }
            i--;
        }

        // copy the remaining elements from array1
        while (arr1Index >= 0) {
            merged[i] = array1[arr1Index];
            arr1Index--;
            i--;
        }

        // copy the remaining elements from array2
        while(arr2Index >= 0) {
            merged[i] = array2[arr2Index];
            arr2Index--;
            i--;
        }

        return merged;
    }
}
