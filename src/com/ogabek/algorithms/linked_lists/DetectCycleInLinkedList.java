package com.ogabek.algorithms.linked_lists;

public class DetectCycleInLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.addNext(new ListNode(2))
                .addNext(new ListNode(3))
                .addNext(new ListNode(4))
                .addNext(new ListNode(5, listNode));

        System.out.println(detectCycle(listNode));
    }

    /**
     * time: O(n)
     * space: O(1)
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode fast = head.next.next;
        ListNode slow = head.next;

        // STEP 1: check if there's a cycle
        while (fast != slow) {
            // if there's a tail pointing to null, no cycle
            if (fast.next == null || fast.next.next == null) {
                return null;
            }

            // continue looking for a cycle: increment pointers
            fast = fast.next.next;
            slow = slow.next;
        }

        // STEP 2: Find the cycle start
        slow = head; // reset one of the pointers and increment by one only
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode addNext(ListNode node) {
            this.next = node;
            return this.next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
