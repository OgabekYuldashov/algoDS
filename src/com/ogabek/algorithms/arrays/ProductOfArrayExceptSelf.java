package com.ogabek.algorithms.arrays;

public class ProductOfArrayExceptSelf {

    /**
     * time: O(2n) => O(n)
     * space: O(1) => output array is not considered
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ans[0] = 1;

        // calculate the prefix values
        for(int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        // calculate suffix and multiply to prefix
        int suffix = 1;
        for(int i = len - 1; i >= 0; i--) {
            ans[i] = ans[i] * suffix;
            suffix *= nums[i]; // calculate the suffix for the next iteration
        }

        return ans;
    }

    /**
     * time: O(3n) => O(n)
     * space: O(2n) => O(n)
     */
    public int[] productExceptSelfWithExtraSpace(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        int[] prefix = new int[len];
        int[] suffix = new int[len];
        prefix[0] = 1;
        suffix[len-1] = 1;

        for(int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        for(int i = len - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        for(int i = 0; i < len; i++) {
            ans[i] = prefix[i] * suffix[i];
        }

        return ans;
    }
}
