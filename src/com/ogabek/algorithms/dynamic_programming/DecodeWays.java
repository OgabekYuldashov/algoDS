package com.ogabek.algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {
    Map<Integer,Integer> memo = new HashMap<>();

    /**
     * time: O(n)
     * space: O(1)
     */
    public int numDecodingsTabulationOptimizedSpace(String s) { // "2101"
        if(s.charAt(0) == '0') return 0;

        int oneBack = 1;
        int twoBack = 1;

        for(int i = 1; i < s.length(); i++) {
            int current = 0;
            char ch = s.charAt(i);

            // check if single digit decoding possible
            if(ch != '0') {
                current = oneBack;
            }

            // check if double digit decoding is possible
            int twoDigit = Integer.parseInt(s.substring(i-1, i+1));
            if(twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }

            twoBack = oneBack;
            oneBack = current;
        }

        return oneBack;
    }

    public int numDecodingsMemoization(String s) {
        return numDecodingsMemoized(s, 0); //"226" 0
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    private int numDecodingsMemoized(String s, int start) { // "226" 1
        // if we reach the end of string, return 1 for success
        if(start == s.length()) return 1;

        // check if we have solved the problem before
        if(memo.containsKey(start)) return memo.get(start);

        // if starts with 0, return 0 for failure
        if(s.charAt(start) == '0') return 0;

        // if it is the last char, return 1 for success
        if(start == s.length() -1) return 1;

        int total = numDecodingsMemoized(s, start + 1);
        if(Integer.parseInt(s.substring(start, start + 2)) <= 26) {
            total += numDecodingsMemoized(s, start + 2);
        }

        // save for future use
        memo.put(start, total);

        return total;
    }
}
// "023" -> 0
// "60" -> 0
// "226" -> 3

//                     "226"
//          2 "26"              22 "6"

//    2 2 "6"    2 26 "" -> 1   22 6 "" -> 1

// 2 2 6 "" -> 1