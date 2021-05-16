package com.ogabek.algorithms.linked_lists;

import com.ogabek.data_structures.linked_lists.SinglyLinkedList;

import java.util.Stack;

public class ReverseLinkedList {
    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.append(1);
        linkedList.append(2);
        linkedList.append(3);
        linkedList.append(4);
        linkedList.append(5);
        linkedList.print();

        SinglyLinkedList reversedList = reverseUsingStack(linkedList);
        reversedList.print();

        SinglyLinkedList reversedList2 = reverseOptimal(reversedList);
        reversedList2.print();
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static SinglyLinkedList reverseOptimal(SinglyLinkedList list) {
        int size = list.getSize();
        SinglyLinkedList.Node prev = null;
        SinglyLinkedList.Node current = list.head;
        SinglyLinkedList.Node next;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        return new SinglyLinkedList(prev, size);
    }

    /**
     * time: O(2n) => O(n)
     * space: O(n)
     */
    public static SinglyLinkedList reverseUsingStack(SinglyLinkedList list) {
        Stack<SinglyLinkedList.Node> stack = new Stack<>();

        SinglyLinkedList.Node current = list.head;

        while (current != null) {
            stack.push(current);
            current = current.getNext();
        }

        list = new SinglyLinkedList();
        while (!stack.isEmpty()) {
            SinglyLinkedList.Node node = stack.pop();
            node.setNext(null);
            list.append(node);
        }

        return list;
    }
}
