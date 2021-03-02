package com.ogabek.data_structures.stacks_queues;

public class Stack {
    public static void main(String[] args) {
        Stack stack = new Stack();
        try {
            stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stack.push("1");
        stack.push("2");
        System.out.println("Peek: " + stack.peek());
        try {
            System.out.println("Pop: " + stack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stack.print();
    }

    private int size = 0;
    private Node top;
    private Node bottom;

    public Stack() {
    }

    public String peek(){
        if (isEmpty())
            return null;
        return top.getValue();
    }

    public void push(String value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            top = newNode;
            bottom = newNode;
            size++;
            return;
        }

        newNode.next = top;
        top = newNode;
        size++;
    }

    public String pop() throws Exception {
        if (isEmpty())
            throw new Exception("Cannot pop from empty stack");
        if (top == bottom) {
            bottom = null;
        }

        Node toBePopped = top;
        top = top.next;
        size--;
        return toBePopped.getValue();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
            this.next = null;
        }

        public String  getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }
    }

    public void print() {
        StringBuilder builder = new StringBuilder("size: " + this.size);

        Node current = top;
        while (current != null) {
            builder.append("\n").append(current.value);
            current = current.next;
        }

        System.out.println(builder.toString());
    }
}
