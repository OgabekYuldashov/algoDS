package com.ogabek.data_structures.hash_tables;

import java.util.HashSet;
import java.util.Set;

public class FirstRecurringCharacter {

    public static void main(String[] args) {
        System.out.println(firstRecurringInteger(new int[] {1,2,3,4,1,2}));
        System.out.println(firstRecurringInteger(new int[] {2,5,5,2,6}));

        System.out.println(firstRecurringCharacter("tester"));
    }

    public static Integer firstRecurringInteger(int[] input) {
        Set<Integer> set = new HashSet<>();

        for(int i : input) {
            if(set.contains(i)) return i;
            set.add(i);
        }

        return null;
    }

    public static Character firstRecurringCharacter(String input) {
        Set<Character> set = new HashSet<>();

        for(char c: input.toCharArray()) {
            if(set.contains(c)) return c;
            set.add(c);
        }

        return null;
    }

}
