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
        System.out.println(travelMemoized(2,3));
        System.out.println(travelMemoized(18,10));
    }

    public static int travelMemoized(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return travel(m, n, memo);
    }

    private static int travel(int m, int n, Map<String, Integer> memo) {
        if(m == 0 || n == 0) return 0;
        if(m == 1 && n == 1) return 1;
        String key = m + "," + n;
        if(memo.containsKey(key)) return memo.get(key);

        memo.put(key, travel(m-1, n, memo) + travel(m, n-1, memo));
        return memo.get(key);
    }
}
