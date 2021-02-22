package com.ogabek.data_structures.arrays;

public class MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[] {-12,-9,-13, -6,-11,-8,-9, -15,-6}));
        System.out.println(maxSubArray(new int[] {1}));
        System.out.println(maxSubArray(new int[] {0}));
        System.out.println(maxSubArray(new int[] {-1}));
        System.out.println(maxSubArray(new int[] {-100000}));
    }


    /**
     * Kadane's algorithm:
     * Maximum subarray is "either the current element or the current element combined with the previous maximum subarray"
     *
     * @param nums
     * @return
     *
     * RUNNING TIME: O(n)
     */
    public static int maxSubArray(int[] nums) {
        // validation
        if(nums.length == 0)
            throw new IllegalArgumentException("Array should contain elements");

        int localMax = nums[0];
        int globalMax = nums[0];

        for (int i=1; i<nums.length; i++) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            if (localMax > globalMax)
                globalMax = localMax;
        }

        return globalMax;
    }
}
