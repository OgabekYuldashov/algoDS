package com.ogabek.data_structures.arrays;

public class MaximumSubarray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4})); // Expected: 6
        System.out.println(maxSubArray(new int[] {-12,-9,-13, -6,-11,-8,-9, -15,-6})); // Expected: -6
        System.out.println(maxSubArray(new int[] {1})); // Expected: 1
        System.out.println(maxSubArray(new int[] {0})); // Expected: 0
        System.out.println(maxSubArray(new int[] {-1})); // Expected: -1
        System.out.println(maxSubArray(new int[] {-100000})); // Expected: -100000

        System.out.println("******************");

        maxSubArrayIndices(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        maxSubArrayIndices(new int[] {-12,-9,-13, -6,-11,-8,-9, -15,-6});
        maxSubArrayIndices(new int[] {1});
        maxSubArrayIndices(new int[] {0});
        maxSubArrayIndices(new int[] {-1});
        maxSubArrayIndices(new int[] {-100000});
    }


    /**
     * Kadane's algorithm:
     * Maximum subarray is "either the current element or the current element combined with the previous maximum subarray"
     *
     * @param nums array of integers
     * @return the sum of the maximum subarray
     *
     * RUNNING TIME: O(n)
     */
    public static int maxSubArray(int[] nums) {
        // validation
        if(nums.length == 0)
            throw new IllegalArgumentException("Array should contain elements");

        int localMax = nums[0]; // maximum sum from the previous subarray
        int globalMax = nums[0]; // maximum subarray sum encountered so far

        for (int i=1; i<nums.length; i++) {
            localMax = Math.max(nums[i], nums[i] + localMax);
            if (localMax > globalMax)
                globalMax = localMax;
        }

        return globalMax;
    }

    /**
     * Returns the indices of the subarray with maximum sum
     *
     * @param nums array of integers
     * @return array containing the start and end of the subarray with maximum sum
     *
     * RUNNING TIME: O(n)
     */
    public static int[] maxSubArrayIndices(int[] nums) {
        if(nums.length == 0)
            throw new IllegalArgumentException("Array should contain at least one element");

        int[] indices = new int[2];
        int localMax = nums[0];
        int globalMax = nums[0];

        for(int i = 1; i<nums.length; i++) {
            if (nums[i] >= nums[i] + localMax) { // current element is bigger than max
                localMax = nums[i];

                if(localMax>globalMax) { // make current index the start and end of the max subarray
                    indices[0] = i;
                    indices[1] = i;
                }
            } else {
                localMax = nums[i] + localMax;  // current element is included in the max subarray
                indices[1] = i; // make current index the end of the max subarray
            }

            if (localMax > globalMax) {
                globalMax = localMax;
            }
        }
        System.out.println("Max subarray: " + globalMax + "; indices: " + indices[0] + "," + indices[1]);

        return indices;
    }
}
