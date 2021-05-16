package com.ogabek.algorithms.strings;

import java.util.Stack;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompareBruteForce("", "")); // true
        System.out.println(backspaceCompareBruteForce("ab#c", "ad#c")); // true
        System.out.println(backspaceCompareBruteForce("a##c", "#a#c")); // true
        System.out.println(backspaceCompareBruteForce("a#c", "b")); // false

        System.out.println(backspaceCompareBruteForce2("", "")); // true
        System.out.println(backspaceCompareBruteForce2("ab#c", "ad#c")); // true
        System.out.println(backspaceCompareBruteForce2("ab##", "c#d#")); // true
        System.out.println(backspaceCompareBruteForce2("a#c", "b")); // false

        long arrayMemoStartTime = System.nanoTime();
        System.out.println(backspaceCompareBruteForce("a##ca##ca##ca##ca##ca##ca##ca##ca##ca##c", "#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c")); // true
        long arrayMemoEndTime = System.nanoTime();
        System.out.println("1. Stack Based Solution Execution Time: " + (arrayMemoEndTime - arrayMemoStartTime));

        long tabulationStartTime = System.nanoTime();
        System.out.println(backspaceCompareBruteForce2("a##ca##ca##ca##ca##ca##ca##ca##ca##ca##c", "#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c")); // true
        long tabulationEndTime = System.nanoTime();
        System.out.println("2. StringBuilder Based Solution Execution Time: " + (tabulationEndTime - tabulationStartTime));

        long optimalStartTime = System.nanoTime();
        System.out.println(backspaceCompareOptimal("a##ca##ca##ca##ca##ca##ca##ca##ca##ca##c", "#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c#a#c")); // true
        long optimalEndTime = System.nanoTime();
        System.out.println("3. Optimal Solution Execution Time: " + (optimalEndTime - optimalStartTime));
    }

    /**
     * time: O(n+m)
     * space: O(1)
     */
    public static boolean backspaceCompareOptimal(String s, String t) {
        if(s == null || t == null) return false;

        int p1 = s.length() - 1;
        int p2 = t.length() - 1;

        int sBackslashes = 0;
        int tBackslashes = 0;

        while(p1 >= 0 || p2 >= 0) {
            char sChar = 0;
            if(p1 >= 0) {
                sChar = s.charAt(p1);
                if(sChar == '#') {
                    sBackslashes++;
                    p1--;
                    continue;
                } else if(sBackslashes > 0) {
                    p1--;
                    sBackslashes--;
                    continue;
                }
            }

            char tChar = 0;
            if(p2 >= 0) {
                tChar = t.charAt(p2);
                if(tChar == '#') {
                    tBackslashes++;
                    p2--;
                    continue;
                } else if (tBackslashes > 0) {
                    p2--;
                    tBackslashes--;
                    continue;
                }
            }

            if (sChar != tChar) return false;
            p1--;
            p2--;
        }

        return p1 == p2;
    }


    /**
     * time: O(n+m)
     * space: O(n+m)
     */
    public static boolean backspaceCompareBruteForce2(String s, String t) {
        if(s == null || t == null) return false;
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        int backspaceCount = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != '#') {
                if(backspaceCount > 0) {
                    backspaceCount--;
                    continue;
                }
                string1.append(c);
            } else {
                backspaceCount++;
            }
        }

        backspaceCount = 0;
        for(int i = t.length() - 1; i >= 0; i--) {
            char c = t.charAt(i);
            if(c != '#') {
                if(backspaceCount > 0) {
                    backspaceCount--;
                    continue;
                }
                string2.append(c);
            } else {
                backspaceCount++;
            }
        }

        return string1.toString().equals(string2.toString());
    }

    /**
     * time: O(n+m)
     * space: O(n+m)
     */
    public static boolean backspaceCompareBruteForce(String s, String t) {
        if(s == null || t == null) return false;
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c != '#') {
                stack1.push(c);
            } else if (!stack1.isEmpty()){
                stack1.pop();
            }
        }
        for(char c : t.toCharArray()) {
            if(c != '#') {
                stack2.push(c);
            } else if (!stack2.isEmpty()){
                stack2.pop();
            }
        }
        while (!stack1.isEmpty()) {
            if(stack1.pop() != stack2.pop()) return false;
        }

        return true;
    }

}
