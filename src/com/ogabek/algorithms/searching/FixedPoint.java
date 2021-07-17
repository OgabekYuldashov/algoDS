package com.ogabek.algorithms.searching;

/**
 * 1064. Fixed Point
 *
 * Given an array of distinct integers arr, where arr is sorted in ascending order, return the smallest
 * index i that satisfies arr[i] == i. If there is no such index, return -1.
 *
 * Input: arr = [-10,-5,0,3,7]
 * Output: 3
 */
public class FixedPoint {
    public int fixedPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        int ans = -1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if(mid == arr[mid]) {
                ans = mid;
                right = mid - 1; // keep searching on the left side to find the leftmost value
            } else if (arr[mid] < mid) { // search the right side
                left = mid + 1;
            } else { // search the left side
                right = mid - 1;
            }
        }

        return ans;
    }
}
