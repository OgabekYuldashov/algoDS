package com.ogabek.algorithms.recursion;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("fibonacciRecursive: " + fibonacciRecursive(11));
        System.out.println("fibonacciIterative: " + fibonacciIterative(11));

    }

    public static Integer fibonacciRecursive(Integer number) {
        if (number < 1) throw new RuntimeException("Only Natural Numbers Are Allowed");
        if (number == 1) return 0;
        if (number == 2) return 1;
        return fibonacciRecursive(number-1) + fibonacciRecursive(number-2);
    }

    public static Integer fibonacciIterative(Integer number) {
        if (number < 1) throw new RuntimeException("Only Natural Numbers Are Allowed");
        if (number == 1) return 0;
        if (number == 2) return 1;

        int prevFiboNumber = 1;
        int fiboNumber = 1;
        for (int i = 4; i <= number; i++) {
            int currFiboNumber = fiboNumber + prevFiboNumber;
            prevFiboNumber = fiboNumber;
            fiboNumber = currFiboNumber;
        }
        return fiboNumber;
    }
}
