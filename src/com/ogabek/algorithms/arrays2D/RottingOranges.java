package com.ogabek.algorithms.arrays2D;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 *
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 */
public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges rottingOranges = new RottingOranges();
        System.out.println(rottingOranges.orangesRotting(new int[][] {
                {2, 1, 1, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 2}
        })); // 3 days
        System.out.println(rottingOranges.orangesRotting(new int[][] {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        })); // 4 days
    }
    private final short[][] directions = new short[][] {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    /**
     * time: O(2m*n) => O(m*n)
     * space: O(m*n)
     */
    public int orangesRotting(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0, ROWS = grid.length, COLS = grid[0].length;

        // 1. loop sequentially: count fresh oranges and put rotten ones into the queue.
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {
                int curr = grid[row][col];
                if(curr != 0) {
                    if(curr == 2) {
                        queue.add(new int[] {row, col});
                    } else {
                        freshOranges++;
                    }
                }

            }
        }

        // 2. BFS: a) increment the minutes at the end of each level and b) decay the adjacent fresh oranges
        int size = queue.size(), minutes = 0;
        while (!queue.isEmpty()) {
            if(size == 0) {
                minutes++;
                size = queue.size();
            }

            int[] currDir = queue.poll();
            size--;

            for(short[] dir : directions) {
                int newRow = currDir[0] + dir[0];
                int newCol = currDir[1] + dir[1];

                if(newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS) continue;
                if(grid[newRow][newCol] == 1) {
                    grid[newRow][newCol] = 2;
                    queue.add(new int[] {newRow, newCol});
                    freshOranges--;
                }
            }
        }

        // 3. compare the count of the oranges
        return freshOranges > 0 ? -1 : minutes;
    }
}
