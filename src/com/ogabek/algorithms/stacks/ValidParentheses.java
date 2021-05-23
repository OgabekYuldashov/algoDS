package com.ogabek.algorithms.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("{[]()}")); // true
        System.out.println(isValid("{{[]()}")); // false
        System.out.println(isValid("{[]()})")); // false
        System.out.println(isValid("")); // true
    }


    /**
     * time: O(n)
     * space: O(n)
     */
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('[', ']');
        map.put('(', ')');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if(map.containsKey(c)) { // c is an opening parenthesis
                stack.push(c);
            } else { // c is a closing parenthesis
                if(stack.isEmpty()) return false;

                Character openingBracket = stack.pop();
                if(map.get(openingBracket) != c) return false;
            }
        }

        return stack.isEmpty();
    }
}
