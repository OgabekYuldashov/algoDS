package com.ogabek.algorithms.searching;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FirstAndLastPositionOfElementInSortedArray {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8))); // [3,4]
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 6))); // [-1,-1]
        System.out.println(Arrays.toString(searchRange(new int[]{}, 8))); // [-1,-1]
    }

    static int[] nums;

    public static int[] searchRange(int[] input, int target) {
        nums = input;

        int foundIndex = binarySearch(0, nums.length - 1, target);

        if(foundIndex < 0) return new int[] {-1, -1}; // target not found

        int left = foundIndex;
        int right = foundIndex;

        // find the left boundary
        while(true) {
            int index = binarySearch(0, left - 1, target);
            if(index < 0) break;

            left = index;
        }

        // find the right boundary
        while(true) {
            int index = binarySearch(right + 1, nums.length - 1, target);
            if(index < 0) break;

            right = index;
        }

        return new int[] {left, right};
    }

    private static int binarySearch(int left, int right, int target) {
        while(left <= right) {
            int mid = (int) Math.floor((left + right)/2);

            if(nums[mid] == target) return mid;

            if(target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
