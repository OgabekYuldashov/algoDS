package com.ogabek.algorithms.arrays2D;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {
    /**
     * time: O(m*n)
     * space: O(1)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new LinkedList<>();
        int row = 0;
        int col = 0;
        short[] dir = new short[] {0, 1};
        boolean changingDir = false;
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (left <= right && bottom >= top) {
            // avoid adding duplicates to the list if we are changing directions
            if (!changingDir) output.add(matrix[row][col]);

            int nextRow = dir[0] + row; // 0
            int nextCol = dir[1] + col; // 3

            // calculate the row and col for next iteration if newRow and newCol are within bounds
            if (nextRow >= top && nextRow <= bottom && nextCol >= left && nextCol <= right) {
                row = nextRow;
                col = nextCol;
                changingDir = false;
            } else {    // if not within bounds, we are changing directions next round but still in the same spot
                changingDir = true;
            }

            // move boundaries inwards and change the directions
            if (nextCol > right) {
                dir = new short[] {1, 0}; // go down
                top++;
            } else if (nextRow > bottom) {
                dir = new short[] {0, -1}; // go left
                right--;
            } else if (nextCol < left) {
                dir = new short[] {-1, 0}; // go up
                bottom--;
            } else if (nextRow < top) {
                dir = new short[] {0, 1}; // got right
                left++;
            }
        }

        return output;
    }


}
