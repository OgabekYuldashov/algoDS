package com.ogabek.algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class KnightProbabilityInChessboard {
    short[][] DIRS = new short[][] {
            {-2, -1},
            {-2,  1},
            {-1,  2},
            {1,   2},
            {2,   1},
            {2,  -1},
            {1,  -2},
            {-1, -2}
    };
    Map<String, Double> memo = new HashMap<>();

    /**
     * time: O(8*n
     */
    public double knightProbabilityMemoization(int n, int k, int row, int column) {
        if(row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        if(k == 0) return 1;

        String key = "k" + k + "r" + row + "c" + column;
        if(memo.containsKey(key)) return memo.get(key);

        double totalProbability = 0;
        for(short[] dir : DIRS) {
            totalProbability += knightProbabilityMemoization(n, k - 1, row + dir[0], column + dir[1]) / 8;
        }

        memo.put(key, totalProbability);
        return totalProbability;
    }

    public double knightProbabilityTabulation(int n, int k, int row, int column) {
        double[][] prevGrid = new double[n][n];
        double[][] currGrid = new double[n][n];

        prevGrid[row][column] = 1; // step 0: probability of knight landing on the starting position is 1 (100%)

        for(int step = 1; step <= k; step++) {
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    for(short[] dir : DIRS) {
                        int newRow = r + dir[0];
                        int newCol = c + dir[1];

                        if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n) {
                            currGrid[r][c] += prevGrid[newRow][newCol] / 8;
                        }
                    }
                }
            }

            prevGrid = currGrid;
            currGrid = new double[n][n];
        }

        double totalProbability = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                totalProbability += prevGrid[r][c];
            }
        }

        return totalProbability;
    }

}
