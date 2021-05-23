package com.ogabek.algorithms.linked_lists;

public class FlattenMultilevelDoublyLinkedList {
    public static void main(String[] args) {

    }

    public Node flatten(Node head) {
        flattenRecursively(head);
        return head;
    }

    // flattens and returns the tail node of a given list
    private Node flattenRecursively(Node node) {
        Node prev = node;
        while(node != null) {
            prev = node;

            // 1. skip nodes with no child
            if(node.child == null) {
                node = node.next;
                continue;
            }

            // 2. if there's a child, adjust references
            Node next = node.next; // keep reference to the next node in the parent list
            node.next = node.child;
            node.child.prev = node;

            // 3. get the tail of the child list
            Node tail = flattenRecursively(node.child);
            node.child = null; // child list has been flattened, remove the child reference

            // 4. adjust the tail references
            if(next != null) {
                tail.next = next;
                next.prev = tail;
                node = next; // advance the pointer to the next
            } else {
                node = tail;
            }

        }

        return prev;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
