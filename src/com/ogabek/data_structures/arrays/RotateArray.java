package com.ogabek.data_structures.arrays;

import java.util.Arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 */
public class RotateArray {

    public static void main(String[] args) {
        // SOLUTION 1:
        Arrays.stream(rotateWithExtraArray(new int[] {1,2,3,4,5,6,7}, 3))
                .forEach(System.out::println);

        System.out.println("**********");

        // SOLUTION 2:
        Arrays.stream(rotateByReversing(new int[] {1,2,3,4,5,6,7}, 3))
                .forEach(System.out::println);
    }

    /**
     * @param nums array of integers
     * @param k number of clockwise rotations to do
     * @return array which has been rotated clockwise k times
     *
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(k)
     */
    public static int[] rotateWithExtraArray(int[] nums, int k) {
        k = k % nums.length;
        int[] temp = new int[k];
        int tempIndex = 0;

        for (int i = nums.length - k; i < nums.length; i++) {
            temp[tempIndex++] = nums[i];
        }

        int leftCursor = nums.length -1 - k;
        for (int j = nums.length-1; j >= 0; j--) {
            if (leftCursor >= 0) {
                nums[j] = nums[leftCursor--];
            } else {
                nums[j] = temp[--tempIndex];
            }
        }

        return nums;
    }

    /**
     * @param nums array of integers
     * @param k number of clockwise rotations to do
     * @return array which has been rotated clockwise k times
     *
     * TIME COMPLEXITY: O(n) => O(2n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int[] rotateByReversing(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length-1);

        reverse(nums, 0, k-1);

        reverse(nums, k, nums.length-1);

        return nums;
    }

    public static void reverse(int[] nums, int left, int right) {
        int temp;
        while (left < right) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
