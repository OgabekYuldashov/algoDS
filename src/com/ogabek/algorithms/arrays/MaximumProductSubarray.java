package com.ogabek.algorithms.arrays;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;

        int result = nums[0];
        int max_so_far = result;
        int min_so_far = result;

        for(int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(min_so_far * curr, max_so_far * curr));
            min_so_far = Math.min(curr, Math.min(min_so_far * curr, max_so_far * curr));

            max_so_far = tempMax;
            result = Math.max(max_so_far, result);
        }

        return result;
    }

    public int maxProductTwoPass(int[] nums) {
        int max = Integer.MIN_VALUE;

        int product = 1;
        for(int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if(val == 0) {
                max = Math.max(max, val);
                product = 1;
            } else {
                product *= val;
                max = Math.max(max, Math.max(product, val));
            }
        }

        product = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];
            if(val == 0) {
                max = Math.max(max, val);
                product = 1;
            } else {
                product *= val;
                max = Math.max(max, Math.max(product, val));
            }
        }

        return max;
    }
}
