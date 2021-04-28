package com.ogabek.algorithms.recursion;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("findFactorialRecursive: " + findFactorialRecursive(5));
        System.out.println("findFactorialIterative: " + findFactorialIterative(5));

    }

    public static Integer findFactorialRecursive(Integer number) {
        if (number < 0) throw new RuntimeException("Only Positive Numbers Are Allowed");
        if(number == 1) return 1;
        return number * findFactorialRecursive(number - 1);
    }

    public static Integer findFactorialIterative(Integer number) {
        if (number < 0) throw new RuntimeException("Only Positive Numbers Are Allowed");

        int factorial = 1;
        while (number > 1) {
            factorial = factorial * number--;
        }
        return factorial;
    }
}
