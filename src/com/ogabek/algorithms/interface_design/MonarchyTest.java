package com.ogabek.algorithms.interface_design;

public class MonarchyTest {
    public static void main(String[] args) {
        Monarchy monarchy = new MonarchyImpl("Jake");
        monarchy.birth("Catherine", "Jake");
        monarchy.birth("Jane", "Catherine");
        monarchy.birth("Farah", "Jane");
        monarchy.birth("Tom", "Jake");
        monarchy.birth("Celine", "Jake");
        monarchy.birth("Mark", "Catherine");
        monarchy.birth("Peter", "Celine");
        System.out.println(monarchy.getOrderOfSuccession());
        monarchy.death("Jake");
        monarchy.death("Jane");
        System.out.println(monarchy.getOrderOfSuccession());
    }
}
