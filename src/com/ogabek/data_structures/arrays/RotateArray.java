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

        System.out.println("**********");

        // SOLUTION 3:
        Arrays.stream(rotateInPlace(new int[] {1,2,3,4,5,6,7}, 3))
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

    /**
     * The most efficient in term of Time and Space.
     *
     * @param nums array of integers
     * @param k number of clockwise rotations to do
     * @return array which has been rotated clockwise k times
     *
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int[] rotateInPlace(int[] nums, int k) {
        k %= nums.length;   // eliminate excessive rotations
        int count = 0;  // number of total swaps. Always n number of swaps are made, where n = nums.length

        for (int start = 0; count<nums.length; start++) {   // increment the starting point of cycles
            int current = start;
            int prevElem = nums[current];

            // A cycle ends when the last element in the cycle
            // is inserted in the starting position
            do {
                int next = (current + k) % nums.length; // each element is k steps away from its correct location.
                int temp = nums[next];
                nums[next] = prevElem;
                current = next;
                prevElem = temp;

                count++; // increasing the count each time a swap is made
            } while (start != current); // we stop the cycle when we're back at the element where we started
        }

        return nums;
    }
}
