package com.ogabek.algorithms.strings;

import java.util.Stack;

public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompareBruteForce("", "")); // true
        System.out.println(backspaceCompareBruteForce("ab#c", "ad#c")); // true
        System.out.println(backspaceCompareBruteForce("ab##", "c#d#")); // true
        System.out.println(backspaceCompareBruteForce("a##c", "#a#c")); // true
        System.out.println(backspaceCompareBruteForce("a#c", "b")); // false
    }

    public static boolean backspaceCompareBruteForce(String s, String t) {
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
