package com.ogabek.algorithms.recursion;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 */
public class KthLargestElement {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[] {3,2,1,5,6,4}, 2)); // 5
        System.out.println(findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4)); // 4

        System.out.println(findKthLargestWithHeap(new int[] {3,2,1,5,6,4}, 2)); // 5
        System.out.println(findKthLargestWithHeap(new int[] {3,2,3,1,2,4,5,5,6}, 4)); // 4
    }


    /**
     * time: O(nlogn)
     * space: O(n)
     */
    public static int findKthLargestWithHeap(int[] nums, int k) {
        if (k > nums.length || k < 1) throw new RuntimeException("Invalid Input");

        // init a maxHeap using priority queue
        PriorityQueue<Integer> mQueue = new PriorityQueue<>(Collections.reverseOrder());

        for(int n : nums) {
            mQueue.add(n);
        }

        while(k > 1) {
            mQueue.poll();
            k--;
        }

        return mQueue.peek();
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
