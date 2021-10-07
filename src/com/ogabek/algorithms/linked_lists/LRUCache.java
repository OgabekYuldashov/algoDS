package com.ogabek.algorithms.linked_lists;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private final Map<Integer, Node> map;
    private final int capacity;
    private int size;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        // if no key found, return -1
        if (!this.map.containsKey(key)) return -1;

        // get the node value
        Node node = this.map.get(key);

        // rearrange
        rearrange(node);

        return node.value;
    }

    public void put(int key, int value) {

        if (this.map.containsKey(key)) {
            Node node = this.map.get(key);
            node.value = value;

            rearrange(node);
        } else {
            Node node = new Node(key, value);
            node.right = this.head;

            if (this.head != null) {
                this.head.left = node;
            }

            if (this.tail == null) {
                this.tail = this.head;
            }

            this.head = node;

            this.map.put(key, node);
            size++;
        }

        // if size > capacity, then remove the tail => at least 2 elems in the list
        if (size > capacity) {
            Node left = this.tail.left;
            left.right = null;
            this.map.remove(this.tail.key);
            this.tail = left;

            size--;
        }
    }

    private void rearrange(Node node) {
        if (size <= 1) return;
        else if (size == 2) {
            Node newTail = node.right != null ? node.right : node.left;
            node.left = null;
            node.right = newTail;
            this.head = node;

            newTail.left = node;
            newTail.right = null;
            this.tail = newTail;
            return;
        }

        // 1 -> 2 -> 3
        if (node == tail) {
            Node left = node.left;
            left.right = null;
            this.tail = left;

            node.left = null;
            node.right = this.head;

            this.head.left = node;
            this.head = node;
        } else if (node != head) {
            Node left = node.left;
            Node right = node.right;

            // link two newighbours
            left.right = right;
            right.left = left;

            // move node to head
            node.left = null;
            node.right = head;
            head.left = node;
            this.head = node;
        }
    }


    static class Node {
        private Node left = null;
        private Node right = null;
        private final int key;
        private int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}


// size = 2

//     1,1 -> 0
//     2,2 -> 0
//     3,3 -


//     3 -> 2

