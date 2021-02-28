package com.ogabek.data_structures.linked_lists;

public class SinglyLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.append(3);
        linkedList.append(4);
        linkedList.remove(1);
        linkedList.insert(1,4);
        linkedList.prepend(2);
        linkedList.insert(0,1);
        linkedList.insert(455,6);
        linkedList.insert(4,5);
        linkedList.print();
        if ((linkedList.head != null)) {
            System.out.println("head: " + linkedList.head.value);
        } else {
            System.out.println("head: null");
        }
        if ((linkedList.tail != null)) {
            System.out.println("tail: " + linkedList.tail.value);
        } else {
            System.out.println("tail: null");
        }
    }

    private int size;
    public Node head;
    public Node tail;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public void append(int value) {
        if (head == null) { // the list is empty
            addInitialNode(value);
            return;
        }

        Node newNode = new Node(value);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public void prepend(int value) {
        if (head == null) { // the list is empty
            addInitialNode(value);
            return;
        }

        Node newNode = new Node(value);
        newNode.next = this.head;
        head = newNode;
        size++;
    }

    public void insert(int index, int value) {
        //validation
        if (index <= 0) {
            prepend(value);
            return;
        }
        if (index >= size) {
            append(value);
            return;
        }

        Node newNode = new Node(value);
        Node leader = traverseTo(index-1);
        Node follower = leader.next;
        leader.next = newNode;
        newNode.next = follower;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Traversal Index out of bounds");

        if (index == 0) { // removing the head
            head = head.next;
            if(head == null)
                tail = head;
            size--;
            return;
        }

        Node leader = traverseTo(index - 1);
        Node unwantedNode = leader.next;
        leader.next = unwantedNode.next;
        if (leader.next == null)
            tail = leader;
        size--;

    }

    private Node traverseTo(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Traversal Index out of bounds");

        Node current = head;
        int count = 0;
        while (count != index) {
            current = current.next;
            count++;
        }

        return current;
    }

    private void addInitialNode(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        size++;
    }

    class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public void print() {
        StringBuilder builder = new StringBuilder("length: " + this.size + "\n[ ");

        Node current = head;
        while (current != null) {
            if (current == head) {
                builder.append(current.value);
            } else {
                builder.append("->").append(current.value);
            }
            current = current.next;
        }
        builder.append(" ]");

        System.out.println(builder.toString());
    }

}
