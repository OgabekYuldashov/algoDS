package com.ogabek.algorithms.interface_design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonarchyImpl implements Monarchy {
    private final Map<String, Person> people = new HashMap<>();
    private final Person monarch;

    public MonarchyImpl(String monarch) {
        this.monarch = new Person(monarch);
        people.put(monarch, this.monarch);
    }

    @Override
    public void birth(String childName, String parentName) {
        if(people.containsKey(parentName)) {
            Person parent = people.get(parentName);
            Person child = parent.addChild(childName);
            people.put(childName, child);
        } else {
            throw new RuntimeException("Parent " + parentName + " does not exist!");
        }
    }

    @Override
    public void death(String name) {
        if(people.containsKey(name)) {
            Person person = people.get(name);
            person.die();
        } else {
            throw new RuntimeException("Person " + name + " was not found in the hierarchy!");
        }
    }

    @Override
    public List<String> getOrderOfSuccession() {
        List<String> successionList = new LinkedList<>();
        dfs(monarch, successionList);
        return successionList;
    }

    private void dfs(Person monarch, List<String> list) {
        if(monarch.isAlive()) list.add(monarch.getName());
        for(Person p : monarch.getChildren()) {
            dfs(p, list);
        }
    }

    static class Person {
        private String name;
        private List<Person> children;
        private boolean isAlive = true;

        Person(String name) {
            this.name = name;
            children = new LinkedList<>();
        }

        private Person addChild(String childName) {
            Person child = new Person(childName);
            children.add(child);
            return child;
        }

        private void die() {
            this.isAlive = false;
        }

        public String getName() {
            return name;
        }

        public List<Person> getChildren() {
            return children;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }
    }
}
