package com.ogabek.algorithms.arrays;

public class TrappingRainwater {
    public static void main(String[] args) {
        System.out.println(trapBruteForce(new int[] {0,1,0,2,1,0,3,1,0,1,2})); // 8
        System.out.println(trapBruteForce(new int[] {0,1,0,2,1,0,1,3,2,1,2,1})); // 6

        System.out.println(trapOptimal(new int[] {0,1,0,2,1,0,3,1,0,1,2})); // 8
        System.out.println(trapOptimal(new int[] {0,1,0,2,1,0,1,3,2,1,2,1})); // 6
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static int trapOptimal(int[] height) {
        if(height.length < 3) return 0;
        int total = 0;

        int leftIndex = 0, rightIndex = height.length - 1;
        int leftMax = 0, rightMax = 0;

        // 1. check which pointer value is less or equal
        // 2. work on the less pointer side: check if the less pointer value has a max value that's greater than itself to act as a wall
        //      Yes -> calculate the water
        //      No -> Update the max value to the current
        // 3. update the cursor inwards
        // 4. Repeat the same steps on the other pointer

        while (leftIndex < rightIndex) {
            int leftValue = height[leftIndex];
            int rightValue = height[rightIndex];
            int currentWater = 0;

            if(leftValue <= rightValue) { // 1. left side is smaller, work on the left side
                // a. does left side have a wall that's higher than itself?
                if(leftMax > leftValue) {
                    // if yes, calculate the water
                    currentWater = leftMax - leftValue;
                } else { // b. otherwise, update the leftMax
                    leftMax = leftValue;
                }
                // move the left index as it was smaller than right
                leftIndex++;
            } else { // 2. right side is smaller, work on the right side
                // a. does right side have a wall that's higher than itself?
                if(rightMax > rightValue) {
                    // if yes, calculate the water
                    currentWater = rightMax - rightValue;
                } else { // b. otherwise, update the rightMax
                    rightMax = rightValue;
                }
                rightIndex--;
            }
            // add the current water to total
            if(currentWater > 0) total += currentWater;
        }

        return total;
    }

    /**
     * time: O(n^2)
     * space: O(1)
     */
    public static int trapBruteForce(int[] height) {
        if(height.length < 3) return 0;
        int total = 0;
        int leftMax = 0, rightMax = 0;

        for(int i = 1; i < height.length; i++) {
            // find the leftMax
            for(int k = 0; k < i; k++) {
                if(height[k] > leftMax) leftMax = height[k];
            }

            // find the rightMax
            for(int j = i + 1; j < height.length; j++) {
                if(height[j] > rightMax) rightMax = height[j];
            }

            // find water for the current position
            int water = Math.min(leftMax, rightMax) - height[i];

            // add water to total if it's a positive number
            if(water > 0) total += water;

            // reset max values
            leftMax = 0;
            rightMax = 0;
        }

        return total;
    }
}
