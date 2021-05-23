package com.ogabek.algorithms.linked_lists;

public class FlattenMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        Node list1 = new Node(1);
        list1.addToList(new Node(2));
        list1.addToList(new Node(3));
        Node node4 = new Node(4);
        list1.addToList(node4);

        Node list2 = new Node(5);
        Node node6 = new Node(6);
        list2.addToList(node6);

        Node list3 = new Node(7);
        list3.child = new Node(8);

        list1.child = list2;
        node4.child = list3;

        Node flatListHead = flatListRecursive(list1);
        flatListHead.printAll();
    }

    /**
     * n -> total number of nodes in the list including the sublist nodes
     * time: O(n)
     * space: O(n) when each list consists of one node, and each of these nodes contains a child,
     * the list becomes vertical. Since recursive solution is a bottom up approach, the call stack
     * size will be "n", and the worst case occurs
     */
    public static Node flatListRecursive(Node head) {
        recurse(head);
        return head;
    }

    // flattens and returns the tail node of a given list
    private static Node recurse(Node node) {
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
            Node tail = recurse(node.child);
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

    /**
     * time: O(n) -> more iterations than "n" since we might iterate over some newly merged child
     * nodes again on the parent level
     *
     * space: O(1)
     */
    public static Node flatListIterative(Node head) {
        Node current = head;
        while(current != null) {
            // 1. skip if there's no child
            if(current.child == null) {
                current = current.next;
                continue;
            }

            // 2. traverse to the tail of the child list
            Node next = current.next; // next node in the parent list
            Node childTail = current.child; // child tail
            while(childTail.next != null) {
                childTail = childTail.next;
            }

            // 3. merge the child tail to the original list
            childTail.next = next;
            if(next != null) {
                next.prev = childTail;
            }

            // 4. merge the child head to the original list
            current.next = current.child;
            current.child.prev = current;
            current.child = null;

            current = current.next;
        }

        return head;
    }

    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }

        public void addToList(Node node) {
            Node tail = this;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
            node.prev = tail;
        }

        public void printAll() {
            System.out.print(val + "<->");
            if(next != null) {
                next.printAll();
            } else {
                System.out.print("null\n");
            }
        }
    }
}
