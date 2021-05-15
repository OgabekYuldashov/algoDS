package com.ogabek.algorithms.arrays;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of the line i is at (i, ai) and (i, 0). Find two lines, which, together with
 * the x-axis forms a container, such that the container contains the most water.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Input: height = [1,2,1]
 * Output: 2
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(maxArea(new int[] {1,1})); // 1
        System.out.println(maxArea(new int[] {4,3,2,1,4})); // 16
        System.out.println(maxArea(new int[] {1,2,1})); // 2
        System.out.println(maxArea(new int[] {7,1,2,3,9})); // 28

        System.out.println("\nBrute Force Solution:\n");

        System.out.println(maxAreaBruteForce(new int[] {1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(maxAreaBruteForce(new int[] {1,1})); // 1
        System.out.println(maxAreaBruteForce(new int[] {4,3,2,1,4})); // 16
        System.out.println(maxAreaBruteForce(new int[] {1,2,1})); // 2
        System.out.println(maxAreaBruteForce(new int[] {7,1,2,3,9})); // 28
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static int maxArea(int[] height) {
        if(height.length < 2) return 0;
        int maxArea = 0;

        int left = 0;   // initialize leftmost pointer
        int right = height.length-1;   // initialize rightmost pointer
        int leftMax, rightMax;

        while (left < right) {
            leftMax = height[left];
            rightMax = height[right];

            int currentArea = Math.min(leftMax, rightMax) * (right - left);
            maxArea = Math.max(maxArea, currentArea);

            if(leftMax <= rightMax) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static int maxAreaBruteForce(int[] height) {
        // not possible to create a container with less than 2 heights
        if(height.length < 2) return 0;
        int max = 0; // max = 28

        for(int i = 0; i < height.length; i++) { // i = 2
            for(int j = i+1; j < height.length; j++) { // j =
                int area = Math.min(height[i], height[j]) * (j - i); // area =  *
                if(area > max) max = area;
            }
        }

        return max;
    }
}
