package com.ogabek.algorithms.arrays;

public class FindMinimumInRotatedSortedArray {
    /**
     * time: O(logn)
     * space: O(1)
     */
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];

        int left = 0;
        int right = nums.length - 1;

        // a. array is not rotated, early return
        if(nums[left] < nums[right]) return nums[left];

        // b. array is rotated
        while (left < right) {
            int mid = left + (right - left) / 2;
            int val = nums[mid];

            if(val > nums[mid + 1]) return nums[mid + 1];

            if(val < nums[mid -1]) return val;

            if(val > nums[left]) { // search on the right
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
