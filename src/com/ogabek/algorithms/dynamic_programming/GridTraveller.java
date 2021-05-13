package com.ogabek.algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * Say that you are a traveller on a 2D grid. You begin in the top-left corner
 * and your goal is to travel to the bottom right corner. You may move only down or right.
 *
 * In many unique ways can you travel to the goal on a grid with dimensions m * n?
 */
public class GridTraveller {

    public static void main(String[] args) {
        System.out.println(travelMemoizedWithMap(2,3));

        long mapMemoStartTime = System.nanoTime();
        System.out.println(travelMemoizedWithMap(18,10));
        long mapMemoEndTime = System.nanoTime();
        System.out.println("HashMap Memo Execution Time: " + (mapMemoEndTime - mapMemoStartTime));

        long arrayMemoStartTime = System.nanoTime();
        System.out.println(travelTabulation(18, 10));
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Tabulation Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));
    }

    public static int travelMemoizedWithMap(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return travelMemoizedWithMap(m, n, memo);
    }

    private static int travelMemoizedWithMap(int m, int n, Map<String, Integer> memo) {
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return 1;
        String key = m + "," + n;
        Integer value = memo.get(key);
        if(value != null) return value;
        value = travelMemoizedWithMap(m-1, n, memo) + travelMemoizedWithMap(m, n-1, memo);
        memo.put(key, value);
        return value;
    }


    public static int travelTabulation(int m, int n) {
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return 1;
        int[][] table = new int[m+1][n+1];
        table[1][1] = 1;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // if we're within bounds, increment right cell by current value
                if(j+1 <= n) table[i][j+1] += table[i][j];
                // if we're within bounds, increment down cell by current value
                if(i+1 <= m) table[i+1][j] += table[i][j];
            }
        }
        return table[m][n];
    }
}
