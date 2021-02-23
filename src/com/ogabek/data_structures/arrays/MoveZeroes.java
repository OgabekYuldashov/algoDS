package com.ogabek.data_structures.arrays;

import java.util.Arrays;

public class MoveZeroes {

    public static void main(String[] args) {
        Arrays.stream(moveZeroes(new int[]{0,1,0,3,12}))
                .forEach(i -> System.out.print(i + ", "));

        System.out.println("************");

        Arrays.stream(moveZeroes(new int[]{2,3,4,1,0,3,12}))
                .forEach(i -> System.out.print(i + ", "));

    }

    /**
     * @param nums array of integers containing zeroes
     * @return array of integers with all 0's moved to the end
     *
     * RUNNING TIME: O(n)
     */
    public static int[] moveZeroes(int[] nums) {
        int pos = 0;
        boolean isZeroEncountered = false;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                if (isZeroEncountered) {
                    nums[pos] = nums[i];
                    nums[i] = 0;
                }

                pos++;
            } else {
                isZeroEncountered = true;
            }
        }

        return nums;
    }
}
