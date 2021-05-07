package com.ogabek.algorithms.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    public static void main(String[] args) {
        //0, 1, 1, 2, 3, 5, 8, 13, 21, 34... -> Fib sequence
        //1, 2, 3, 4, 5, 6, 7, 8,  9,  10... -> n th Fib number

        System.out.println("Fib 1: " + fibWithArrayMemo(1));
        System.out.println("Fib 9: " + fibWithArrayMemo(9));

        long arrayMemoStartTime = System.nanoTime();
        System.out.println("Fib 90: " + fibWithArrayMemo(90));
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("Array Memo Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));

        long mapMemoStartTime = System.nanoTime();
        System.out.println("Fib 90: " + fibWithMapMemo(90));
        long mapMemoEndTime = System.nanoTime();
        System.out.println("HashMap Memo Execution Time: " + (mapMemoEndTime - mapMemoStartTime));


    }

    public static int fibWithArrayMemo(int n) {
        int[] memo = new int[n+1];
        return fibWithArrayMemo(n, memo);
    }

    private static int fibWithArrayMemo(int n, int[] memo) {
        if(n <= 1) return 0;
        if(n <= 3) return 1;
        if(memo[n] != 0) return memo[n];

        memo[n] = fibWithArrayMemo(n-1, memo) + fibWithArrayMemo(n-2, memo);
        return memo[n];
    }


    public static int fibWithMapMemo(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibWithMapMemo(n, memo);
    }

    private static int fibWithMapMemo(int n, Map<Integer, Integer> memo) {
        if(n <= 1) return 0;
        if(n <= 3) return 1;
        if(memo.containsKey(n)) return memo.get(n);

        memo.put(n, fibWithMapMemo(n-1, memo) + fibWithMapMemo(n-2, memo));
        return memo.get(n);
    }

}
