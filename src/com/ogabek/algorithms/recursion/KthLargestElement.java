package com.ogabek.algorithms.recursion;

import java.util.Random;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 */
public class KthLargestElement {

    public static void main(String[] args) {
        System.out.println();
    }


    static int[] arr;

    /**
     * time: Average Case: O(2n) -> O(n); Worst case (Sorted Array): O(n^2)
     * space: O(1)
     */
    public static int findKthLargest(int[] nums, int k) {
        arr = nums;
        quickSelect(0, arr.length - 1, arr.length - k); // time: O(2n) => O(n)
        return arr[arr.length - k];
    }

    private static void quickSelect(int left, int right, int k) {
        if(left < right) {
            Random rand = new Random();
            int randPivotIndex = left + rand.nextInt(right - left);

            int pi = partition(left, right, randPivotIndex);

            if(k < pi) {
                quickSelect(left, pi - 1, k);
            } else if (k > pi) {
                quickSelect(pi + 1, right, k);
            }
        }
    }

    private static int partition(int left, int right, int randPivotIndex) {
        int pivot = arr[randPivotIndex]; // store the pivot value
        swap(randPivotIndex, right); // move the pivot to the far right

        int pi = left; // initialize final pivot index to the leftmost position

        for(int i = left; i < right; i++) {
            if(arr[i] < pivot) {
                // swap elements at i and pi
                swap(pi, i);
                pi++;
            }
        }
        swap(pi, right);

        return pi;
    }

    private static void swap(int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
